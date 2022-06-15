package com.gmail.marekhovancak;

import javax.swing.*;
import java.awt.*;

public class ErorrDialog extends JDialog {

    JPanel container;
    JLabel msg;

    public ErorrDialog( JFrame frame, Boolean modal ) {
        super( frame, modal );

        setTitle( "Error Notification" );
        setIconImage( new ImageIcon( this.getClass().getResource("/res/erorr-icon.png" ) ).getImage() );
        setPreferredSize( new Dimension( 400, 180 ) );
        setLocationRelativeTo( frame );
        setResizable( false );

        container = new JPanel( new BorderLayout() );

        msg = new JLabel();

        msg.setIcon( new ImageIcon( new ImageIcon( this.getClass().getResource( "/res/erorr-icon.png" ) ).getImage().getScaledInstance( 30, 30, Image.SCALE_DEFAULT ) ) );
        msg.setText( "  Erorr" );

        init();

        container.add( msg, BorderLayout.CENTER );
        add( container );
        pack();
    }

    private void init() {

        container.setBackground( Color.decode( "#191923" ) );
        container.setBorder( BorderFactory.createEmptyBorder( 0, 40, 0, 10 ) );

        msg.setFont( new Font( Font.SANS_SERIF, Font.PLAIN, 15 ) );
        msg.setForeground( Color.WHITE );

    }
}
