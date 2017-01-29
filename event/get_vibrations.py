#!/usr/bin/env python
# -*- coding: utf-8 -*-
#import RPi.GPIO as GPIO 
import sys 
import time
import urllib2
import json

def get_bracelet(server):
    device_identifier = "bracelet_nicolas"
    http_get = urllib2.urlopen("http://"+server+":8080/api/bracelet/deviceidentifier?deviceidentifier="+device_identifier)
    my_bracelet =  json.load(http_get)
    http_get.close()

    return my_bracelet


def get_vibrations(server):
    my_bracelet = get_bracelet(server)
    vibrations = urllib2.urlopen("http://"+server+":8080/api/vibration/true?idbracelet="+str(my_bracelet['id']))
    vibrationsTrue = json.load(vibrations)
    vibrations.close()
    
    print "Number of vibrations coming : " + str(len(vibrationsTrue))   
    print vibrationsTrue
    for vib in vibrationsTrue:
        id = vib['id']
        http_put = urllib2.urlopen("http://localhost:8080/api/vibration/put?idvibration="+str(id)+"&state=false")

    return len(vibrationsTrue)

if __name__ == '__main__':
    if(len(sys.argv) > 1 ):
        print "GPIO choosen : "+ sys.argv[1]
        try:
            print 'Press Ctrl-C to quit.'
            while True:
                try:
				    server = "localhost"
                    vibrations = get_vibrations(server)
                except:
                    vibrations = 0    
                
                if(vibrations > 0):
                    i = 0
                    while i < vibrations:
                        print "vibration : " + str(i)
                        gpio_output = sys.argv[1]
                        GPIO.setmode(GPIO.BCM)
                        GPIO.setup(int(sys.argv[1]), GPIO.OUT)
                        GPIO.output(int(gpio_output) ,GPIO.HIGH)
                        time.sleep(1)
                        GPIO.output(int(gpio_output), GPIO.LOW)
                        time.sleep(1)
                        GPIO.cleanup()
                        i = i + 1
        finally:
            GPIO.cleanup()
    else:
        print "GPIO Argument is missing."            
