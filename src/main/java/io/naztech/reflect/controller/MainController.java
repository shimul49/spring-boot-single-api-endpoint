package io.naztech.reflect.controller;


import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {
    @GetMapping("/api/v1/jsonRequest")
    public ResponseEntity<?> resolveResource(
            @RequestParam String controller, @RequestParam String methodName,
            @RequestParam HttpMethod httpMethod, @RequestBody(required = false) Map<String, Object> body
    ) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> controllerClass = Class.forName("io.naztech.reflect.controller." + controller + "Controller");
        Method methodToInvoke = Arrays.stream(controllerClass.getDeclaredMethods())
                .filter(method -> method.getName().equals(methodName))
                .findFirst()
                .orElseThrow(() -> new NoSuchMethodException("Method not found"));
        Object [] methodArgs = prepareMethodArguments(methodToInvoke, body);

        Object controllerInstance = controllerClass.getDeclaredConstructor().newInstance();
        Object result = methodToInvoke.invoke(controllerInstance, methodArgs);
        return ResponseEntity.ok(result);
    }

    private Object[] prepareMethodArguments(Method method, Map<String, Object> requestBody) {
        List<Object> args = new ArrayList<>();

        /*Parameter[] parameters = method.getParameters();
        if( parameters.length == 0 ) {
            return new Object[0];
        }*/
        for(Parameter parameter : method.getParameters() ) {
            if( parameter.isAnnotationPresent(RequestParam.class) ) {
                String paramName = parameter.getDeclaredAnnotation(RequestParam.class).value();

                if( requestBody.containsKey(paramName) ) {
                    args.add((requestBody.get(paramName)));
                } else {
                    //
                }
            } else if( parameter.isAnnotationPresent(RequestBody.class) ) {
                args.add(requestBody);
            }
        }
        return args.toArray();
    }

    @GetMapping("/api/v1")
    public String hi() {
        return "Hello Reflection";
    }
}
