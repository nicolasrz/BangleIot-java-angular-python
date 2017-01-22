"# Event RPI"

cd /mysql-connector-python-2.0.4
python setup.py install

#Lauch script
python get_vibrations.py {PIN GPIO BCM}

Example : python get_vibrations/py 14
