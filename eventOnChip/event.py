import CHIP_IO.GPIO as GPIO
GPIO.setup("GPIO3", GPIO.IN)
while(True):
        if GPIO.input("GPIO3"):
                print("Sensor Touch Activated !")
        else:
                print("LOW")