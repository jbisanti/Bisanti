/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bisanti.swingx;

import java.awt.Component;
import javax.swing.text.JTextComponent;

/**
 * Written and authored by Jason Bisanti. Free to use and reproduce.
 * <br><br>
 *
 * @author Jason Bisanti
 */
public final class FindDialog 
{
    private static FindFrame findFrame;
    
    private FindDialog(){}
    
    public static void showFindDialog(Component relativeTo, JTextComponent textComponent)
    {
        int caret = 0;
        if(findFrame != null)
        {            
            if(textComponent.equals(findFrame.getTextComponent()))
            {
                caret = findFrame.getCaretPosition();
            }
            
            findFrame.dispose();
            findFrame = null;
        }
        
        findFrame = new FindFrame();
        findFrame.setCaretPosition(caret);
        findFrame.setTextComponent(textComponent);
        findFrame.setLocationRelativeTo(relativeTo);
        findFrame.setVisible(true);
    }
    
}
