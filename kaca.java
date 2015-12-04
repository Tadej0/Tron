package com.company;
import java.util.ArrayList;
import java.util.Random;

public class kaca {

    koordinate trenutno = new koordinate();
    koordinate dump = new koordinate();
    ArrayList<koordinate> tabela = new ArrayList<>();
    int sirina_kvadrata=1;
    int visina_kvadrata=1;
    int pretekloGibanje=5;

    boolean alive = true;

    public void smrtInRojstvo(){
        //Smrt
        tabela.clear();
        pretekloGibanje=5;
        //Rojstvo
        trenutno.x = risanje.sirina/2;
        trenutno.y = risanje.visina/2;
    }

    public boolean kontaktSelfie(int x, int y){
        int trenutniX = x;
        int trenutniY = y;
        int i;
        int pon=0;
        for(i=0;i<tabela.size();i++){
            dump=tabela.get(i);
            if((trenutniX==dump.x)&&(trenutniY==dump.y)){
                pon++;
            }
            if (pon>0){
                return true;
            }
        }
        return false;
    }

    public boolean kontaktStena(){
        if(!mejeStene()){
            alive=false;
            smrtInRojstvo();
            return true;
        }else
            return false;
    }

    public boolean mejeStene(){
        if(trenutno.x < 0)              return false;
        if(trenutno.x > risanje.sirina) return false;
        if(trenutno.y<0)                return false;
        if(trenutno.y>risanje.visina)   return false;
        else
            return true;
    }

    public void povecajSirinoKvadrata(int plus){
        this.sirina_kvadrata+=plus;
    }

    public void povecajVisinoKvadrata(int plus){
        this.visina_kvadrata+=plus;
    }

    public void povecajObeStranici(int plus){
        povecajSirinoKvadrata(plus);
        povecajVisinoKvadrata(plus);
    }

    public void gor(){
        if (pretekloGibanje == 0) {
            trenutno.y += 0;
        } else {
            trenutno.y -= 1;
            pretekloGibanje = 1;
        }
    }

    public void dol(){
        if (pretekloGibanje == 1) {
            trenutno.y -= 0;
        } else {
            trenutno.y += 1;
            pretekloGibanje = 0;
        }
    }
    public void levo(){
        if (pretekloGibanje == 2) {
            trenutno.x -= 0;
        } else {
            trenutno.x -=1;
            pretekloGibanje = 3;
        }
    }
    public void desno(){
        if (pretekloGibanje == 3) {
            trenutno.x += 0;
        } else {
            trenutno.x += 1;
            pretekloGibanje = 2;
        }
    }
    public void lokacija(int x,int y){
        this.trenutno.x = x;
        this.trenutno.y = y;
    }

    public void dodajVtabelo1(int lokacijaX, int lokacijaY){
        koordinate dump = new koordinate();
        dump.x=lokacijaX;
        dump.y=lokacijaY;
        tabela.add(dump);
    }
    public void dodajVtabelo2(koordinate tmp){
        int x = tmp.x;
        int y = tmp.y;
        dodajVtabelo1(x, y);
    }

    public float stenaGor(){
        return (trenutno.y);
    }
    public float stenaDol(){
        return(risanje.visina-trenutno.y);
    }
    public float stenaLevo(){
        return(trenutno.x);
    }
    public float stenaDesno(){
        return (risanje.sirina-trenutno.x);
    }

    //BEDEN ALGORITEM ZA IZBIRO KAM SE BO PREMAKNIL KER NE UPOŠTEVA OBEH PODATKOV!
    //NAREDI BOLJŠEGA!!!

    public void AIzogibSteni(int natancnost){
        if((trenutno.x+natancnost>risanje.sirina)||(trenutno.x-natancnost<0)) {
            if (stenaGor() > stenaDol()) gor();
            else dol();
        }
        if((trenutno.y-natancnost<0)||(trenutno.y+natancnost>risanje.visina)) {
            if (stenaDesno() > stenaLevo()) desno();
            else levo();
        }
    }

    public int povprasevanjeLevo(){
        int tmpX;
        int bufferL = 0;

        for(tmpX=trenutno.x;tmpX>0;tmpX--){
            if(kontaktSelfie(tmpX,trenutno.y)==false) bufferL++;
            else break;
        }
        return bufferL;
    }
    public int povprasevanjeDesno(){
        int tmpX;
        int bufferD = 0;

        for(tmpX=trenutno.x;tmpX<risanje.sirina;tmpX++){
            if(kontaktSelfie(tmpX,trenutno.y)==false) bufferD++;
            else break;
        }
        return bufferD;
    }

    public int povprasevanjeDol(){
        int tmpY;
        int bufferD = 0;

        for(tmpY=trenutno.y;tmpY<risanje.visina;tmpY++){
            if(kontaktSelfie(trenutno.x,tmpY)==false) bufferD++;
            else break;
        }
        return bufferD;
    }
    public int povprasevanjeGor(){
        int tmpY;
        int bufferG = 0;

        for(tmpY=trenutno.y;tmpY>0;tmpY--){
            if(kontaktSelfie(trenutno.x,tmpY)==false) bufferG++;
            else break;
        }
        return bufferG;
    }

    public void AIzogibSebi(int dolzina_senzorja){
        int povecuj;
        if(pretekloGibanje==0){//DOL
            for(povecuj=trenutno.y;povecuj<(trenutno.y+dolzina_senzorja); povecuj++) {
                if(kontaktSelfie(trenutno.x,povecuj) == true){
                    //Dela kot mora!
                    if(povprasevanjeLevo()>povprasevanjeDesno())levo();
                    else desno();
                }
            }
        }

        if(pretekloGibanje==1){//GOR
            for(povecuj=trenutno.y;povecuj>(trenutno.y-dolzina_senzorja);povecuj--){
                if(kontaktSelfie(trenutno.x,povecuj) == true){
                    if(povprasevanjeLevo()>povprasevanjeDesno())levo();
                    else desno();
                }
            }
        }

        if(pretekloGibanje==2){//levo
            for(povecuj=trenutno.x;povecuj<(trenutno.x+dolzina_senzorja);povecuj++){
                if(kontaktSelfie(povecuj,trenutno.y) == true){
                    if(povprasevanjeGor()>povprasevanjeDol())gor();
                    else dol();
                }
            }
        }

        if(pretekloGibanje==3){//desno
            for(povecuj=trenutno.x;povecuj>(trenutno.x-dolzina_senzorja);povecuj--){
                if(kontaktSelfie(povecuj,trenutno.y) == true) {
                    if(povprasevanjeGor()>povprasevanjeDol())gor();
                    else dol();
                }
            }
        }

    }

    public void AI(int natancnost){

        AIzogibSteni(natancnost);
        AIzogibSebi(natancnost);

    }
}
