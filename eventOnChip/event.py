import CHIP_IO.GPIO as GPIO
import time

# Define callback function
def mycallback(channel):
    # Sample is just to print something, but other logic could go here
    print("Sensor Touch Activated!")

# GPIO3 = XIO-P4, using PocketCHIP nomenclature
GPIO.setup("GPIO3", GPIO.IN)
# Trigger when we go from LOW to HIGH to match code above
GPIO.add_event_detect("GPIO3", GPIO.RISING, mycallback)

# Dummy loop to keep the script alive
dead = False
while not dead:
    try:
        time.sleep(0.2)
    except KeyboardInterrupt:
        dead = True

# Cleanup
GPIO.remove_event_detect("GPIO3")
GPIO.cleanup()