package com.gmail.marekhovancak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;



public class Frame extends JFrame {

    String[] chars = { "x²", "√x", "AC", "DEL", "%", "π", "±", "÷", "7", "8", "9", "×", "4", "5", "6", "−", "1", "2", "3", "+", "0", ".", "ANS", "=" };
    Button[] buttons = new Button[ chars.length ];

    JPanel container, buttonContainer, labelContainer;

    JLabel currentOperandLabel, previousOperandLabel;

    ErorrDialog erorrs;

    Calculator calculator ;


    public Frame() {

        super( "Calculator" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setResizable( false );
        setLocation( 750,250 );

        container = new JPanel( new FlowLayout() );

        buttonContainer = new JPanel( new GridLayout( 6, 4 ) );

        labelContainer = new JPanel();

        currentOperandLabel = new JLabel();
        currentOperandLabel.setHorizontalAlignment( JTextField.RIGHT );

        previousOperandLabel = new JLabel();
        previousOperandLabel.setHorizontalAlignment( JTextField.RIGHT );

        labelContainer.add( previousOperandLabel );
        labelContainer.add( currentOperandLabel );

        erorrs = new ErorrDialog( this, true );


        calculator = new Calculator( currentOperandLabel, previousOperandLabel, erorrs );


        int loop = buttons.length;
        for ( int i = 0; i < loop; i++ ) {


            if ( chars[ i ].equals( "=" ) ) buttons[ i ] = new Button( chars[ i ], Color.decode( "#2d7dd2" ) );
            else if ( Character.isDigit( chars[ i ].charAt( 0 ) ) || chars[ i ].equals( "." ) )
                buttons[ i ] = new Button( chars[ i ], Color.decode( "#2E2F31" ) );
            else buttons[ i ] = new Button( chars[ i ], Color.decode( "#1e1e24" ) );

            buttonContainer.add( buttons[ i ] );

            int finalI = i;
            buttons[ i ].addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                    String text = buttons[ finalI ].getText();

                    switch ( text ) {
                        case "0": case "1": case "2": case "3": case "4": case "5":
                        case "6": case "7": case "8": case "9": case ".":
                            calculator.appendNumber( text ); break;
                        case "÷": case "×": case "−": case "+":
                        case "%": case "x²": case "√x":
                            calculator.chooseOperation( text ); break;
                        case "=":
                            calculator.compute(); break;
                        case "±":
                            calculator.negation(); break;
                        case "AC":
                            calculator.clear(); break;
                        case "DEL":
                            calculator.delete(); break;
                        case "ANS":
                            calculator.displayAns(); break;
                        case "π":
                            calculator.displayPI(); break;
                        default:
                            break;
                    }

                    calculator.updateLabel();
                }

            });
        }

        container.add( labelContainer );
        container.add( buttonContainer );

        add( container );

        init();

        pack();

    }

    public void init() {

        setIconImage( new ImageIcon( this.getClass().getResource("/res/icon.png") ).getImage() );
        getContentPane().setBackground( Color.WHITE );
        setPreferredSize( new Dimension( 400, 555 ) );

        container.setBackground( Color.decode( "#191923" ) );

        buttonContainer.setPreferredSize( new Dimension( 390, 380 ) );
        buttonContainer.setBackground( Color.decode( "#191923" ) );
        buttonContainer.setBorder( BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) );


        labelContainer.setPreferredSize( new Dimension( 380, 130 ) );
        labelContainer.setBackground( Color.decode( "#191923" ) );

        currentOperandLabel.setFont( new Font( Font.SANS_SERIF, Font.BOLD, 50 ) );
        currentOperandLabel.setForeground( Color.WHITE );
        currentOperandLabel.setPreferredSize( new Dimension( 380, 90 ) );
        currentOperandLabel.setBorder( BorderFactory.createEmptyBorder( 0, 15, 0, 15 ) );

        previousOperandLabel.setFont( new Font( Font.SANS_SERIF, Font.PLAIN, 20 ) );
        previousOperandLabel.setForeground( Color.WHITE );
        previousOperandLabel.setPreferredSize( new Dimension( 380, 40 ) );
        previousOperandLabel.setBorder( BorderFactory.createEmptyBorder( 0, 15, 0, 15 ) );
    }

}