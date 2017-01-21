<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<title>Raspberry PI</title>
<style>
td, tr{
	padding:1px;
}
</style>
</head>

<body>
	<div class="container">
		<table class="table table-striped">
			<tr>
				<th style="text-align:center">Action</th>
				<th style="text-align:center">Name</th>
				<th style="text-align:center"></th>
				<th style="text-align:center"></th>
				<th style="text-align:center">Name</th>
				<th style="text-align:center">Action</th>
				
			</tr>
			
			<?php
			$tableauGauche = [0,2,3,4,-2,17,27,22,0,10,9,11,-2];
			$tableauDroite = [-1,-1,-2,14,15,18,-2,23,24,-2,25,8,7];
			
			
			for($i = 1; $i < 14; $i++)
			{
				?>
			<tr>
				<form action="action.php" method="GET">
				<td style="text-align:center">
				<input type="hidden" name="id" id="id-<?php echo $tableauGauche[$i-1]; ?>" value="<?php echo $tableauGauche[$i-1]; ?>" />
				<?php 
					if($tableauGauche[$i-1] > 0)
					{
				?>
				<span class="pastille glyphicon glyphicon-record" id="pastille-<?php echo $tableauGauche[$i-1]; ?>" style="color:red;cursor:pointer" onclick="changeValue('<?php echo $tableauGauche[$i-1]; ?>')"></span>
				<select name="value" id="value-<?php echo $tableauGauche[$i-1]; ?>" style="display:none"><option>0</option><option>1</option></select>
				
					<?php } ?>
				</td>
				</form>
				<td style="text-align:center">
				<?php switch($tableauGauche[$i-1]){
					case 0:
						echo "3.3v";
					break;
					case -1:
						echo "5v";
					break;
					case -2:
						echo "Ground";
					break;
					case -3:
						echo "ID_SD";
					break;
					default:
						echo "GPIO".$tableauGauche[$i-1];
					break;

				}?>
				
				</td>
				<td>•</td>
				<td>•</td>
				<td style="text-align:center">
				<?php
				switch($tableauDroite[$i-1]){
					case 0:
						echo "3.3v";
					break;
					case -1:
						echo "5v";
					break;
					case -2:
						echo "Ground";
					break;
					case -3:
						echo "ID_SD";
					break;
					default:
						echo "GPIO".$tableauDroite[$i-1];
					break;

				}
				?>
				</td>
				<form action="action.php" method="GET">
				<td style="text-align:center">
				<input type="hidden" id="id-<?php echo $tableauDroite[$i-1]; ?>" name="id" value="<?php echo $tableauDroite[$i-1]; ?>" />
				<?php 
					if($tableauDroite[$i-1] > 0)
					{
				?>
				<span class="pastille glyphicon glyphicon-record" id="pastille-<?php echo $tableauDroite[$i-1]; ?>" style="color:red;cursor:pointer" onclick="changeValue('<?php echo $tableauDroite[$i-1]; ?>')"></span>
				<select name="value" id="value-<?php echo $tableauDroite[$i-1]; ?>" style="display:none"><option>0</option><option>1</option></select>
				
					<?php } ?>
				</td>
				</form>
			</tr>
			<?php
			}
			?>
		</table>
	</div>
	<script type="text/javascript">
	$(document).ready(function(){
		$(".pastille").each(function(){
			var id =this.id.split('-')[1].replace('r','');
			var idComplet =this.id;
			$.ajax({
				url:"getValue.php",
				data:{
					id: id
				},
				success:function(res){
					if(res == "1")
					{
						$("#"+idComplet).css("color","green");
						$("#value-"+id).val("1");
						console.log(id + " . "+res);	
					}
					else{
						
						$("#"+idComplet).css("color","red");
						$("#value-"+id).val("0");
					}
				}
				
			});
			
		});
		
	});
	
	
	function changeValue(test)
	{
		
		if($("#value-"+test).val() == "1")
		{
			$.ajax({
				url:"action.php",
				data:{
						value:"0",
						id:$("#id-"+test).val()
				},
				success:function(data){
					$("#pastille-"+test).css("color","red");
					$("#value-"+test).val("0");
				}
			});
			
		}
		else{
			$.ajax({
				url:"action.php",
				data:{
						value:"1",
						id:$("#id-"+test).val()
				},
				success:function(data){
					$("#pastille-"+test).css("color","green");
					$("#value-"+test).val("1");
				}
			});
			
		}
		
	}
	</script>
</body>
</html>
<?php
//system("gpio -g mode 14 out");
//system("gpio -g write 14 1");

?>
