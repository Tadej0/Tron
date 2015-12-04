package com.company;

import processing.core.PApplet;
import java.util.ArrayList;

public class risanje extends PApplet {

    public static final int sirina = 800;
    public static final int visina = 800;
    koordinate tmp = new koordinate();
    kaca snake = new kaca();

    public void setup() {
        size(800, 800);
        background(255);
        frameRate(100);
        snake.lokacija(width / 2, height / 2);
    }

    public void draw(){
        stroke(0, 0, 0);
        fill(0);
        rect(snake.trenutno.x, snake.trenutno.y, snake.sirina_kvadrata, snake.visina_kvadrata);

        if(keyPressed) {
            switch (keyCode) {
                case UP:
                    snake.gor();
                    break;
                case DOWN:
                    snake.dol();
                    break;
                case LEFT:
                    snake.levo();
                    break;
                case RIGHT:
                    snake.desno();
                    break;
            }//KONEC "CASE"
        }
        else {
            switch (snake.pretekloGibanje){
                case 0:
                    snake.dol();
                    break;
                case 1:
                    snake.gor();
                    break;
                case 2:
                    snake.desno();
                    break;
                case 3:
                    snake.levo();
                    break;
            }
        }//Konec "case"

        //AI natancnost: AI(int natancnost);
        snake.AI(10);

        if(snake.tabela.size()>2){
            if(snake.kontaktStena()==true) {
                snake.smrtInRojstvo();
                background(255);
            }
            if(snake.kontaktSelfie(snake.trenutno.x, snake.trenutno.y)==true){
                snake.smrtInRojstvo();
                background(255);
            }
        }
        snake.dodajVtabelo1(snake.trenutno.x,snake.trenutno.y);
    }
}

