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
@WebFilter()
public class AddResponseHeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
      FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader(
          "Access-Control-Allow-Origin", "*");
        chain.doFilter(request, response);
    }
}
