/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ega.SpringWS;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author sa
 */

//якщо потрібно до кожної відповіді додавати якусь інформацію, то це робиться за допомогою WebFilter.
//@WebFilter("/filter-response-header/*")
//Додаємо до кожної відповіді заголовок Access-Control-Allow-Origin (щоб браузер не лаявся на CORS)
//Додаємо заголовок Cache-Control: no-store який вказує, що будь-які кеші будь-якого типу (приватні або загальні) не повинні зберігати відповідь, що містить заголовок
//Додаємо заголовок Content-Security-Policy": "frame-ancestors 'none' Заголовок, що використовується для вказівки,
//чи може бути відповідь укладена в елемент <frame>, або ні. Цей заголовок захищає від атак клікджекінгу
//Додаємо заголовок X-Content-Type-Options: nosniff Заголовок, що вказує браузеру завжди використовувати тип MIME,
//оголошений у Content-Type заголовку, а не намагатися визначити тип MIME на основі вмісту файлу. 
//Цей заголовок з nosniff значенням не дозволяє браузерам виконувати аналіз MIME та некоректно інтерпретувати відповіді як HTML
//X-Frame-Options: DENY Заголовок , що використовується для вказівки, чи може бути відповідь укладена в елемент <frame>, або ні.
//Для відповіді API не потрібно, щоб вона була укладена в будь-який з цих елементів.
//Надання не дозволяє будь-якому домену формувати відповідь, що повертається викликом API.
//Цей заголовок зі значенням захищає від атак клікджекінгу
@WebFilter()
public class AddResponseHeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
      FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader(
          "Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader(
          "Cache-Control", "no-store");
        httpServletResponse.setHeader(
          "Content-Security-Policy", "frame-ancestors 'none'");
        httpServletResponse.setHeader(
          "Strict-Transport-Security", "max-age=63072000; includeSubDomains; preload");
        httpServletResponse.setHeader(
          "X-Content-Type-Options", "nosniff");
        httpServletResponse.setHeader(
          "X-Frame-Options", "DENY");
        chain.doFilter(request, response);
    }
}
