package com.gmail.marekhovancak;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    public Button( String text, Color color )  {

        super( text );
        setBorder( BorderFactory.createLineBorder( Color.decode( "#191923" ), 3) );
        setBackground( color );
        setForeground( Color.decode( "#C0C0C0" ) );
        setFont( new Font( Font.SANS_SERIF, Font.BOLD, 30 ) );
        setEnabled( true );
        setFocusPainted( false );

        addMouseListener( new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground( Color.decode( "#191716" ) );
                setForeground( Color.WHITE  );
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground( color );
                setForeground( Color.decode( "#C0C0C0" ) );
            }

        });
    }

}
