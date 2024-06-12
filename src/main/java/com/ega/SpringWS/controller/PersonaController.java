/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ega.SpringWS.controller;

import com.ega.SpringWS.HttpRequestUtils;
import com.ega.SpringWS.models.Persona;
import com.ega.SpringWS.interfaces.PersonaInterface;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ega.SpringWS.models.Answer;
import com.ega.SpringWS.models.LogRecord;
import com.ega.SpringWS.services.LogRecordService;
import java.time.LocalDateTime;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author sa
 */
//Ця анотація говорить SPRING, що це контроллер, його повинно включити до контекста SPRING (щоб він мав можливість керувати ім)
//Також ця анотація автоматично преобразовує любу відповідь до JSON формату.
@RestController

//Ця анотація задає шлях до кінцевої крапки (ендпоінт) сервісу.
@RequestMapping("/api/v1/persons")

//Ця анотація відноситься до компоненту Lombok. Вона допомогає створити усі конструктори класів та перемених яки відносятся до данного класу.
//Тут він потрібен для того, щоб ініціалізувати PersonaService service і таким чином включити його в область видимості фреймворка SPRING
//(дивись в документаціі к фреймворку "впровадження залежностей через конструктор")
@AllArgsConstructor
public class PersonaController {
    //Визначаємо, що в нашому контролері буде використовуватись сервіс service. 
    //В цій сервіс ми будемо передавати дані який вже реалізує нашу бізнес-логіку.
    private final PersonaInterface service;
    //private final LogRecordService logService;

//Ця анотація задає шлях до кінцевої крапки (ендпоінт) сервісу. Тут треба зайти на http://localhost:8080/api/v1/persons/show
//щоб отримати весь перелік персон. Також тут указуеться що метод повинен виконуватись за допомогою GET
    @GetMapping("list")
    public Answer showAllPersons(){
        Answer ans;
        
        System.out.println("List persons");
        ans = service.showAllPersons();
        
        return ans;
    }
    
    
    @GetMapping("test")
    public Answer test(){
        
        //повертаємо користувачу результат який був опрацьован в сервісі.
        Answer ans = Answer.builder().status(true).descr("request successful").result("Поточний час: "+LocalDateTime.now()).build();
        return ans;
    }
    
    
//Ця анотація говорить що наступний запит повинен бути типу GET   
//Тут ми говоримо, що путь http://localhost:8080/api/v1/persons/find/[РНОКПП] буде використовуватись як сервіс для пошуку персони з заданим РНОКПП.
//Де [РНОКПП] - це параметр який потрібно замінити на існуючий рнокпп.

    @GetMapping("find/{rnokpp}")
    //Анотація @PathVariable говорить SPRINGу що параметр rnokpp треба прийняти через адресну строку
    public Answer find(@PathVariable String rnokpp){
        Answer ans;
        System.out.println("Find person: "+rnokpp);
        System.out.println("ip: "+HttpRequestUtils.getClientIpAddress()
                +"; Method: "+HttpRequestUtils.getHttpMethod()
                +"; Path: "+HttpRequestUtils.getPath()
        );
        
        
        //повертаємо користувачу результат який був опрацьован в сервісі.
        ans = service.find(rnokpp);
        return ans;
    }

    @GetMapping("find/firstname/{firstName}")
    //Анотація @PathVariable говорить SPRINGу що параметр rnokpp треба прийняти через адресну строку
    public Answer findByFirstName(@PathVariable String firstName){
        Answer ans;
        
        
        //повертаємо користувачу результат який був опрацьован в сервісі.
        ans = service.findByFirstName(firstName);
        return ans;
    }
    
    @GetMapping("find/lastname/{lastName}")
    //Анотація @PathVariable говорить SPRINGу що параметр rnokpp треба прийняти через адресну строку
    public Answer findLastName(@PathVariable String lastName){
        Answer ans;
        
        
        //повертаємо користувачу результат який був опрацьован в сервісі.
        ans = service.findByLastNameWith(lastName);
        return ans;
    }

    @GetMapping("find/range/age/{startAge}/{endAge}")
    //Анотація @PathVariable говорить SPRINGу що параметр rnokpp треба прийняти через адресну строку
    public Answer findLastName(@PathVariable Integer startAge, @PathVariable Integer endAge){
        Answer ans;
        
        
        //повертаємо користувачу результат який був опрацьован в сервісі.
        ans = service.findByAgeRange(startAge,endAge);
        return ans;
    }
    
//Ця анотація говорить що наступний запит повинен бути типу POST   
//Тут ми говоримо, що путь http://localhost:8080/api/v1/persons буде використовуватись для додавання персони в базу даних.
//Параметри запиту повинні додаватись у тілі в JSON форматі.
    @PostMapping("add")
    public Answer addPersona(@RequestBody Persona persona){
        Answer ans = Persona.isValid(persona);
        
        if(!ans.getStatus())return ans;
    /*    
        System.out.println("==============================================================================================");
        System.out.println("Додана персона: "+persona.toString());
        System.out.println("ip: "+HttpRequestUtils.getClientIpAddress()
                +"; Метод: "+HttpRequestUtils.getHttpMethod()
                +"; Шлях: "+HttpRequestUtils.getPath()
                +"; Тіло запиту: "+persona.toJSON().toString()
        );
        */
    
        //повертаємо користувачу результат який був опрацьован в сервісі.
        ans =  service.addPersona(persona);
        
        return ans;
    }
    
//Ця анотація говорить що наступний запит повинен бути типу PUT   
//Тут ми говоримо, що путь http://localhost:8080/api/v1/persons буде використовуватись для оновленя персони в базу даних.
//зіставлення персони буде виконуватись по ID
//Параметри запиту повинні додаватись у тілі в JSON форматі.
    @PutMapping("update")
   //Анотація @RequestBody говорить SPRINGу що тіло запиту треба преобразовувати з JSON в обʼєкт Persona
    public Answer updatePersona(@RequestBody Persona persona){
        Answer ans;
        //повертаємо користувачу результат який був опрацьован в сервісі.
        ans = service.updatePersona(persona);

        return ans;
    }

//Ця анотація говорить що наступний запит повинен бути типу DELETE   
//Тут ми говоримо, що путь http://localhost:8080/api/v1/persons/[РНОКПП] буде використовуватись для видалення персони з бази даних.
//зіставлення персони буде виконуватись по РНОКПП
    @DeleteMapping("delete/{rnokpp}")
    //Анотація @PathVariable говорить SPRINGу що це параметр який треба прийняти через адресну строку
    public Answer deletePersona(@PathVariable String rnokpp){
        Answer ans;
        //повертаємо користувачу результат який був опрацьован в сервісі.
        ans = service.deletePersona(rnokpp);

        return ans;
    }

    @GetMapping("checkup/{rnokpp}")
    //Анотація @PathVariable говорить SPRINGу що це параметр який треба прийняти через адресну строку
    public Answer checkupPersona(@PathVariable String rnokpp){
        Answer ans;
        //повертаємо користувачу результат який був опрацьован в сервісі.
        ans =  service.checkup(rnokpp);

        return ans;
    }

    @GetMapping("check/{rnokpp}")
    //Анотація @PathVariable говорить SPRINGу що це параметр який треба прийняти через адресну строку
    public Answer checkPersona(@PathVariable String rnokpp){
        Answer ans;
        //повертаємо користувачу результат який був опрацьован в сервісі.
        ans = service.checkPersona(rnokpp);

        return ans;
    }
   
}

