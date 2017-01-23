#!/usr/bin/env python
# -*- coding: utf-8 -*-
import RPi.GPIO as GPIO 
import sys 
import time
import urllib2
import json
def send_vibrations():
	associated_bracelet = get_associated_bracelet()
	associated_bracelet_id = str(associated_bracelet['id'])
	associated_bracelet_device_identifier = associated_bracelet['deviceIdentifier']
	http_fake_post = urllib2.urlopen("http://localhost:8080/api/vibration/post?idbracelet="+associated_bracelet_id)
	http_fake_post.close()

def get_associated_bracelet():
    device_identifier = "bracelet_nicolas"
    http_get = urllib2.urlopen("http://localhost:8080/api/bracelet/associated/deviceidentifier?deviceidentifier="+device_identifier)
    associated_bracelet =  json.load(http_get)
    http_get.close()

    return associated_bracelet

    
if __name__ == '__main__':
    
    if(len(sys.argv) > 1 ):
    	print "GPIO choosen : "+ sys.argv[1]
        try:
            print 'Press Ctrl-C to quit.'
            while True:
	            gpio_output = int(sys.argv[1])
	            GPIO.setmode(GPIO.BCM)
	            GPIO.setup(gpio_output, GPIO.IN)
	            if GPIO.input(gpio_output):
	             	send_vibrations()
	             	time.sleep(1)
	            GPIO.cleanup()
        finally:
            GPIO.cleanup()
    else:
        print "GPIO Argument is missing. Listenner on : ?"         
