package com.bcafinance._01springbootrestapi.controllers;


import com.bcafinance._01springbootrestapi.handler.ResponseHandler;
import com.bcafinance._01springbootrestapi.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/taketour")
public class TakeTourController {

    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping("/welcome")
    public String getTakeTour(){
        return ConstantMessage.WELCOME_MESSAGE;
    }

    @PostMapping("/readytostart")
    public String postTakeTour(){
        return ConstantMessage.TAKE_TOUR;
    }

    @GetMapping("/runnerz")
//    public ResponseEntity<Object> executionClass(@RequestBody List<String> lsEmail) throws Exception {
    public ResponseEntity<Object> executionClass() throws Exception {
//        String[] strArr = new String[lsEmail.size()];
//
//        for(int i=0;i<strArr.length;i++)
//        {
//            strArr[i] = lsEmail.get(i);
//        }
//        System.out.println(System.getProperty("user.dir"));
//        SMTPCore sc = new SMTPCore();
//        ConfigProperties.getEmailPassword();
//                System.out.println(sc.sendMailWithAttachment(strArr,
//                "INI HANYA TEST",
//                        new ReadTextFileSB("\\data\\template-BCAF.html").getContentFile(),
//                        "SSL",
//                        new String[] {ResourceUtils.getFile("classpath:\\data\\sample.docx").getAbsolutePath()}));
//        System.out.println(System.getProperty("user.dir")+"\\my.ini");

//        String a = (Crypto.performEncrypt(String.valueOf(System.currentTimeMillis()))+Crypto.performEncrypt(lsEmail.get(0))).substring();
//        System.out.println();

        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SEND_EMAIL, HttpStatus.CREATED,null,null,null);
    }
}