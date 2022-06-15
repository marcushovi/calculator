package com.gmail.marekhovancak;


import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

import java.math.BigInteger;
import java.math.MathContext;

public class Calculator {

    private JLabel currentOperandLabel, previousOperandLabel;
    private String operation, currentOperand , previousOperand, ans;
    private ErorrDialog erorrDialog;

    public MathContext mc = new MathContext(30 );

    BigDecimal result ;
    BigDecimal prev ;
    BigDecimal current ;

    public Calculator( JLabel currentOperandLabel, JLabel previousOperandLabel, ErorrDialog erorrDialog ) {

        this.currentOperandLabel = currentOperandLabel;
        this.previousOperandLabel = previousOperandLabel;
        this.erorrDialog = erorrDialog;
        ans = "0";
        clear();
        updateLabel();

    }

    public void clear() {
        previousOperand = "";
        currentOperand = "0";
        operation = null;
    }

    public void delete() {

        if ( currentOperand.length() == 1 || ( currentOperand.length() == 2 && currentOperand.contains( "-" ) ) )
            currentOperand = "0";

        if ( currentOperand.length() > 0 && !currentOperand.equals( "0" ) ) currentOperand = currentOperand.substring( 0, currentOperand.length()-1 );

    }

    public void appendNumber( String number ) {

        if ( number.equals( "." ) && currentOperand.contains( "." ) ) return;

        if ( currentOperand.equals( "0" ) && !number.equals( "." ) ) currentOperand = "";

        currentOperand += number;
    }

    public void chooseOperation( String operation ) {

        if ( currentOperand.isEmpty() && !operation.equals( "x²" ) ) return;

        if ( !previousOperand.isEmpty() ) {
            compute();
        }


        previousOperand = currentOperand;



        this.operation = operation;
        currentOperand = "";
    }

    public void compute() {

        if ( previousOperand.isEmpty() || currentOperand.isEmpty() && !operation.equals( "x²" ) ) return;

        if ( !operation.equals( "x²" )  ) {
            current = new BigDecimal( currentOperand, mc );
        }

        result = new BigDecimal( BigInteger.ZERO, mc );
        prev = new BigDecimal( previousOperand, mc );

        switch ( operation ) {
            case "+":
                result = prev.add( current, mc ) ;
                break;
            case "−":
                result = prev.subtract( current, mc );
                break;
            case "×":
                result = prev.multiply( current, mc );
                break;
            case "÷":
                if ( Double.parseDouble( currentOperand ) != 0 ) {
                    result = prev.divide( current, mc );
                }
                else {
                    erorrDialog.msg.setText( "  Division by zero." );
                    erorrDialog.setVisible( true );
                }
                break;
            case "x²":
                result = prev.pow( 2, mc ) ;
                break;
            case "√x":
                if ( Double.parseDouble( currentOperand ) >= 0 ) {
                    result = current.sqrt( mc );
                }
                else {
                    erorrDialog.msg.setText( "  Attempted square root of negative." );
                    erorrDialog.setVisible( true );
                }
                break;
            case "%":
                result = prev.remainder( current );
                break;
            default:
                result = new BigDecimal( "0", mc );
                break;
        }

        ans = currentOperand = result.toPlainString();
        operation = null;
        previousOperand = "";

    }

    public void displayAns() {
        currentOperand = ans;
    }

    public void displayPI() {
        currentOperand = Double.toString( Math.PI );
    }

    public void negation() {

        if ( currentOperand.equals( "0" ) || currentOperand.isEmpty() ) return;

        current = new BigDecimal( currentOperand, mc );

        currentOperand = current.negate( mc ).toPlainString();
    }

    public void updateLabel() {


        if ( currentOperand.length() > 20 ) currentOperandLabel.setFont( new Font( Font.SANS_SERIF, Font.BOLD, 20 ) );
        else if ( currentOperand.length() > 12 ) currentOperandLabel.setFont( new Font( Font.SANS_SERIF, Font.BOLD, 30 ) );
        else currentOperandLabel.setFont( new Font( Font.SANS_SERIF, Font.BOLD, 50 ) );

        if ( previousOperand.length() > 31 ) previousOperandLabel.setFont( new Font( Font.SANS_SERIF, Font.PLAIN, 15 ) );
        else previousOperandLabel.setFont( new Font( Font.SANS_SERIF, Font.PLAIN, 20 ) );



        currentOperandLabel.setText( currentOperand );

        if ( operation != null ) {

            if (operation.equals( "x²" ) ) previousOperandLabel.setText( previousOperand + "²");
            else if (operation.equals( "√x" ) ) previousOperandLabel.setText( "√" );
            else previousOperandLabel.setText( previousOperand + " " + operation );

        }
        else
            previousOperandLabel.setText( previousOperand );
    }




}
