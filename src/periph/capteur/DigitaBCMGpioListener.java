package periph.capteur;

/*
 * #%L
 * **********************************************************************
 * ORGANIZATION  :  Pi4J
 * PROJECT       :  Pi4J :: Java Examples
 * FILENAME      :  DigitaBCMGpioListener.java
 *
 * This file is part of the Pi4J project. More information about
 * this project can be found here:  https://www.pi4j.com/
 * **********************************************************************
 * %%
 * Copyright (C) 2012 - 2021 Pi4J
 * %%
 * #L%
 */

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.util.Console;
import com.pi4j.util.ConsoleColor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This example code demonstrates how to setup a listener
 * for GPIO pin state changes on the RaspberryPi
 * using the Broadcom chipset GPIO pin numbering scheme.
 *
 * @author Robert Savage
 * adapted by Lionel Buathier
 */
public class DigitaBCMGpioListener{

    final private GpioPinDigitalInput myButton;
    final private GpioController gpio;
 
    public DigitaBCMGpioListener(Pin pin) {
        try{       
            GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));
        }catch(Exception ex){
            System.out.println("catchhhh");
        }

    
        // create gpio controller
        gpio = GpioFactory.getInstance();

        // provision broadcom gpio pin #5 as an input pin - No pull down needed for grove push button
        //final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiBcmPin.GPIO_05, "MyButton", PinPullResistance.PULL_DOWN);
        myButton = gpio.provisionDigitalInputPin(pin, PinPullResistance.PULL_DOWN); // pull down intégrée sur grove push button

        // unexport the GPIO pin when program exits
        myButton.setShutdownOptions(true);

    }
    
    public boolean mesure(){
        return myButton.getState().isHigh();
    }
    
    
}
