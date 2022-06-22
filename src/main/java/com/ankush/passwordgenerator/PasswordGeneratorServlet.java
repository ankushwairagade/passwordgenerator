/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ankush.passwordgenerator;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.Arrays;
import java.util.Random;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 *
 * @author kapture
 */

@WebServlet(name = "Generate Password", urlPatterns = {"/PasswordGeneratorServlet"})
public class PasswordGeneratorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        
        
          response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
      
      //  out.println("<h2>Welcome to Register Servlet</h2>");
        
        String length =request.getParameter("password_length");
        String number =request.getParameter("number");
        String symbols =request.getParameter("symbols");
        String lowercase =request.getParameter("lowercase");
        String uppercase =request.getParameter("uppercase");
       
    
        /*
        
        out.println("<h2> length : "+length+" </h2>");
        out.println("<h2> number : "+number+" </h2>" );
        out.println("<h2> symbols : "+symbols+" </h2>" );
        out.println("<h2> lowercase : "+lowercase+" </h2>" );
        out.println("<h2> uppercase : "+uppercase+" </h2>" );  */
        
        int len=0,ul=0,ll=0,num=0,sp=0; 
        len=parseInt(length);
        if(number!=null){num=1;}
        if(symbols!=null){sp=1;}
        if(lowercase!=null){ll=1;}
        if(uppercase!=null){ul=1;}
        
        
        String str;
        RequestDispatcher rd=request.getRequestDispatcher("index.html");
           rd.include(request, response);
        boolean f=true;
        
        if( ul !=0 || ll!=0 || num !=0 || sp != 0){
                
        char[] ch= generatePassword(len,ul,ll,num,sp);
  
               f=false; 
            str =new String(ch);
         
            out.println();
            out.println("<div class=pass>");
            out.println("<h2> Generated password : "); 
                    out.print("<h2>"+str+"</h2>");
              out.println("</div>");
        }
        
        if(f){
             out.println();
             out.println("<div class=pass>");
             out.println("<h2>Select check box atleast one</h2>");
              out.println("</div>");
        }
        
        
    }
    
    
        private static char[] generatePassword(int length,int upperletter ,int  lowerletter, int number, int special) {
        String capitalCaseLetters = "";
        String lowerCaseLetters="";
        String specialCharacters = "";
        String numbers = "";

        if(upperletter ==1){capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";}
        if(lowerletter==1){lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";}
        if(number ==1){ numbers = "1234567890";}
        if(special==1){specialCharacters = "~!@$%^&*()_+{}[]:;<>,.?/|";}

        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

            

        for(int i = 0; i< length ; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        
        return password;
    }
    
    
}
