/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author Ilya
 */
public class sendingSMS {
    
    Desktop desktop;
    
    void sendingSMS (String login, String password, String message, String numbers) 
            throws URISyntaxException, IOException{
        String url = "https://lcab.smsintel.ru/lcabApi/sendSms.php?login=" 
                + login
                +"&password="
                + password
                +"&txt="
                + message
                +"&to="
                +numbers;
        desktop.browse(new URI(url));
    }
}
