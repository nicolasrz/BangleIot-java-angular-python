#!/usr/bin/env python
# -*- coding: utf-8 -*-
import mysql.connector 
import RPi.GPIO as GPIO 
import sys 
import time
def get_vibrations():
    device_identifier = "bracelet_nicolas"
    conn = mysql.connector.connect(host="adresse of server api",user="taptapapi",password="taptapapi", database="taptapapi")
    cursor = conn.cursor()
    cursor.execute("SELECT bracelet_id FROM bracelet WHERE device_identifier = '" + device_identifier +"'")
    bracelet_associated = cursor.fetchone()
    bracelet_associated = bracelet_associated[0]
    cursor.execute("SELECT * FROM vibration WHERE bracelet_id = '"+str(bracelet_associated)+"' AND state = 1")
    vibrations = cursor.fetchall()
	if(len(vibrations) > 0 ):
        print str(len(vibrations)) + " vibrations à envoyer"
    vibs_to_send = len(vibrations)
    for vib in vibrations:
        id = vib[0]
        state = vib[1]
        bracelet_id = vib[2]
        cursor.execute("UPDATE vibration SET state = 0 WHERE id = "+str(id)+ " AND bracelet_id="+str(bracelet_id))
        conn.commit()
    conn.close()

    return vibs_to_send 

if __name__ == '__main__':
    print "GPIO choosen : "+ sys.argv[1]
    if(len(sys.argv) > 1 ):
        try:
             print 'Press Ctrl-C to quit.'
             while True:
                 vibrations = get_vibrations()
                 if(vibrations > 0):
                     i = 0
                     while i < vibrations:
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
