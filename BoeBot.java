package boebot;
import stamp.core.*;

public class BoeBot{
        MotorAansturing motor;
        Botsingdetectie botsingdetectie;
        FysiekeIndicator fysiekeindicator;
        Aansturing aansturing;
        Lijnvolger lijnvolger;
        Bluetooth bluetooth;
        Afstandsbediening afstandsbedieningir;

        boolean routeAfgerond;
        boolean routeplannerPauze;
        char[] route;
        static int currentCase = 4;
        static int currentStep = 0;

        public BoeBot(){
                motor = new MotorAansturing();
                afstandsbedieningir = new Afstandsbediening(motor, 11);
                botsingdetectie = new Botsingdetectie();
                aansturing = new Aansturing();
                lijnvolger = new Lijnvolger();
                bluetooth = new Bluetooth();

                motor.setSnelheid(100);
                motor.setRichting(true);
                routeAfgerond = false;
                routeplannerPauze = true;
                int[] coordinates;


                int btcode = 0;
                while(true){

                        btcode = bluetooth.checkBt();

                        if(btcode == 3){
                                System.out.println("Coördinaten ontvangen!");
                                coordinates = bluetooth.getCoordinates();
                                for(int i = 0; i <coordinates.length; i++){
                                 System.out.println(coordinates[i]);
                                }
                                route = aansturing.berekenRoute(coordinates[0], coordinates[1], coordinates[2], coordinates[3], coordinates[4], coordinates[5], coordinates[6]);
                                routeAfgerond = true;
                                currentStep = 0;
                                for(int i = 0; i < route.length; i++){
                                  System.out.println(route[i]);
                                  if(route[i] == ' '){ break;}
                                }
                        }else if(btcode == 2){
                            System.out.println("Route ontvangen!");
                            route = bluetooth.getRoute();
                            routeAfgerond = true;
                            currentStep = 0;
                            for(int i = 0; i < route.length; i++){
                                  System.out.println(route[i]);
                                  if(route[i] == ' '){ break;}
                                }
                        }else if(btcode == 1){
                          afstandsbedieningir.verwerkSignaal(bluetooth.getRemoteControl());
                        }else if(btcode == 4){
                          System.out.println("Snelheid: " + bluetooth.getSpeed());
                          motor.setSnelheid(bluetooth.getSpeed());

                        }else if(btcode == 5){
                            if(bluetooth.getChar() == 'p'){
                                 routeplannerPauze = false;
                                  motor.noodStop();
                            }else if(bluetooth.getChar() == 'h'){
                                  System.out.println("test");
                                  routeplannerPauze = true;
                                 if(!routeAfgerond){
                                  routeAfgerond = true;
                                  currentStep = 0;
                                  currentCase  = 0;
                                }

                            }
                        }
                        afstandsbedieningir.verwerkSignaal(afstandsbedieningir.detect());
                        obstakelDetectie();
                        if(routeplannerPauze){
                          if(routeAfgerond){
                                 routeAfgerond = routeplanner();
                          }
                        }
                        CPU.delay(100);
                }
        }
        public void obstakelDetectie(){
                if(botsingdetectie.detectObject() == 1 || botsingdetectie.detectObject() == 2 || botsingdetectie.detectObject() == 3 ){
                        System.out.println("obstakel gedetecteerd! ");
                        motor.noodStop();
                        motor.setRichting(false);
                        motor.start();
                        motor.noodStop();
                        routeplannerPauze = false;
                        bluetooth.sendChar('b');
               }
        }

        public boolean routeplanner(){
                int getalLijnvolger = lijnvolger.readSensor();
                switch(getalLijnvolger){
                        case 0:
                                if(currentCase != 0){
                                        motor.setSnelheid(100);
                                        motor.setRichting(true);
                                        motor.rijden();
                                        currentCase = 0;
                                }
                                break;
                        case 1:
                            if(currentCase != 1){
                              motor.setSnelheidL(10);
                              motor.setSnelheidR(100);
                              motor.setRichting(true);
                              motor.rijden();
                              currentCase = 1;
                            }
                            break;
                        case 2:
                                if(currentCase != 2){
                                    motor.setSnelheidL(100);
                                    motor.setSnelheidR(10);
                                    motor.setRichting(true);
                                    motor.rijden();
                                    currentCase = 2;
                                }
                                break;
                        case 4:
                                if(currentCase != 4){
                                        if(route[currentStep] != ' '){

                                                verwerkAansturing(route[currentStep]);
                                                currentStep++;
                                                 bluetooth.sendChar('k');
                                                System.out.println(route[currentStep-1]);
                                                CPU.delay(1000);
                                                motor.rijden();
                                        }else{
                                                motor.noodStop();
                                                System.out.println("klaar");
                                                bluetooth.sendChar('f');
                                                return false;
                                        }
                                        currentCase = 4;
                                }
                                break;
                        case 5:
                              if(currentCase != 5){
                                motor.noodStop();
                                motor.setSnelheid(30);
                                System.out.println("Gat!!");
                                currentCase = 5;
                                CPU.delay(1000);
                                motor.setRichting(false);
                                motor.rijden();
                                CPU.delay(1000);
                                motor.noodStop();
                                 bluetooth.sendChar('g');
                                return false;
                               // route = ontwijkBoebot(int xx, int xy, int x, int y, int rotation)
                              }
                        default:
                               // motor.noodStop();
        }
          return true;
        }

        public void verwerkAansturing(char opdracht){
                switch(opdracht){
                case 'v':
                       motor.rijden();
                       CPU.delay(100);
                       break;
                case 'a':
                        CPU.delay(1200);
                        motor.noodStop();
                      CPU.delay(200);
                      motor.turn(180);
                      break;
                case 'r':
                      motor.setSnelheid(100);
                      motor.setRichting(true);
                      motor.rijden();
                      CPU.delay(1200);
                      motor.noodStop();
                      CPU.delay(200);
                      motor.turn(90);
                      break;
                case 'l':
                    motor.setSnelheid(100);
                    motor.setRichting(true);
                    motor.rijden();
                    CPU.delay(1200);
                    motor.noodStop();
                    CPU.delay(200);
                    motor.turn(270);
                    break;
               default:
                   motor.noodStop();
                   break;
                }
        }
}