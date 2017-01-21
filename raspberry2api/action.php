<?php
$id = $_GET["id"];
$value = $_GET["value"];

system("gpio -g mode ".$id." out "); 
system("gpio -g write ".$id." ".$value);

?>
