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

        char[] route;
        static int currentCase = 4;
        static int currentStep = 0;

        public BoeBot(){
                motor = new MotorAansturing();
                afstandsbedieningir = new Afstandsbediening(motor);
                //botsingdetectie = new Botsingdetectie();
                aansturing = new Aansturing(4,4);
                lijnvolger = new Lijnvolger();
                bluetooth = new Bluetooth();

                motor.setSnelheid(100);
                motor.setRichting(true);
                routeAfgerond = false;

                System.out.println("de huidige stappen:" );
                //variabele om functies in en uit te schakelen.
                boolean afstandsbediening = true;
                boolean obstakelDetectie = false;
                boolean routeplanner = true;
                boolean sparcours = false;
                int btcode = 0;
                while(true){
                        btcode = bluetooth.checkBt();
						if (btcode == 3)
						{
							System.out.println("Coördinaten ontvangen!");
							bluetooth.filterData();
						}
							
                        else if(btcode == 2){
                          System.out.println("Route ontvangen!");
                          route = bluetooth.getRoute();
                          routeAfgerond = true;
                          currentStep = 0;
                          System.out.println(route[3]);
                        }else if(btcode == 1){
                          afstandsbedieningir.verwerkSignaal(bluetooth.getAfstandsbediening());
                        }
                        if(obstakelDetectie){
                                obstakelDetectie();
                        }
                        if(afstandsbediening){
                        }
                        if(routeplanner){
                          if(routeAfgerond){
                               routeAfgerond = routeplanner();
                          }
                        }
                        if(sparcours){

                        }
                        CPU.delay(100);
                }
        }
        public void obstakelDetectie(){
                if(botsingdetectie.detectEdge()){
                        CPU.writePin(CPU.pin1,true);
                        motor.noodStop();
                        motor.setRichting(false);
                        motor.start();
                        CPU.delay(2000);
                        motor.noodStop();
                        motor.turn(180);
                        motor.setRichting(true);
                        motor.start();
                }else if(botsingdetectie.detectObject() == 1){
                        motor.noodStop();
                        motor.setRichting(false);
                        motor.start();
                        CPU.delay(2000);
                        motor.noodStop();
                        motor.turn(90);
                        motor.setRichting(true);
                        motor.start();
                }else if(botsingdetectie.detectObject() == 2){
                        motor.noodStop();
                        motor.setRichting(false);
                        motor.start();
                        CPU.delay(2000);
                        motor.noodStop();
                        motor.turn(270);
                        motor.setRichting(true);
                        motor.start();
                }else if(botsingdetectie.detectObject() == 3){
                        motor.noodStop();
                        motor.setRichting(false);
                        motor.start();
                        CPU.delay(2000);
                        motor.noodStop();
                        motor.turn(180);
                        motor.setRichting(true);
                        motor.start();
                }
        }

        public boolean routeplanner(){
               if(currentStep == 0){
                        verwerkAansturing(route[currentStep]);
                }
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
                                                System.out.println(route[currentStep-1]);
                                                CPU.delay(1000);
                                                motor.rijden();
                                        }else{
                                                motor.noodStop();
                                                System.out.println("klaar");
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
                                CPU.delay(3000);
                                motor.noodStop();
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
                       CPU.delay(200);
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
                currentStep++;
        }
}
