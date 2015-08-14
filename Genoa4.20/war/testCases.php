<?php 
header('Content-Type: text/javascript'); 
header('Cache-Control: no-cache'); 
header('Pragma: no-cache');

$q = trim($_GET['q']) ;

if($q == 1){
	//text case 1
	echo '[';
	echo '{'."code:1,status:\"undamaged\",turns:0,X:1,Y:1".'}'; echo ",\n";
	echo '{'."code:2,status:\"undamaged\",turns:1,X:1,Y:2".'}'; echo ",\n";
	echo '{'."code:3,status:\"undamaged\",turns:2,X:1,Y:3".'}'; echo ",\n";
	echo '{'."code:61,status:\"undamaged\",turns:0,X:1,Y:4".'}'; echo ",\n";
	echo '{'."code:91,status:\"undamaged\",turns:1,X:1,Y:5".'}'; echo ",\n";
	echo '{'."code:111,status:\"undamaged\",turns:2,X:1,Y:6".'}'; echo ",\n";
	echo '{'."code:141,status:\"undamaged\",turns:0,X:1,Y:7".'}'; echo ",\n";
	echo '{'."code:151,status:\"undamaged\",turns:1,X:1,Y:8".'}'; echo ",\n";
	echo '{'."code:161,status:\"undamaged\",turns:2,X:1,Y:9".'}'; echo ",\n";
	echo '{'."code:171,status:\"undamaged\",turns:0,X:1,Y:10".'}';
	echo ']';
}

if($q == 2){
	//text case 2
	echo '[';
	echo '{'."code:1,status:\"undamaged\",turns:0,X:1,Y:1".'}'; echo",\n";
	echo '{'."code:2,status:\"undamaged\",turns:1,X:2,Y:2".'}'; echo",\n";
	echo '{'."code:3,status:\"undamaged\",turns:2,X:3,Y:3".'}'; echo",\n";
	echo '{'."code:61,status:\"undamaged\",turns:0,X:4,Y:4".'}'; echo",\n";
	echo '{'."code:91,status:\"undamaged\",turns:1,X:5,Y:5".'}'; echo",\n";
	echo '{'."code:111,status:\"undamaged\",turns:2,X:6,Y:6".'}'; echo",\n";
	echo '{'."code:141,status:\"undamaged\",turns:0,X:7,Y:7".'}'; echo",\n";
	echo '{'."code:151,status:\"undamaged\",turns:1,X:8,Y:8".'}'; echo",\n";
	echo '{'."code:161,status:\"undamaged\",turns:2,X:9,Y:9".'}'; echo",\n";
	echo '{'."code:171,status:\"undamaged\",turns:0,X:10,Y:10".'}';
	echo ']';
}

if($q == 3){
	//text case 3
	echo '[';
	echo '{'."code:1,status:\"undamaged\",turns:0,X:11,Y:1".'}'; echo",\n";
	echo '{'."code:2,status:\"undamaged\",turns:1,X:12,Y:2".'}'; echo",\n";
	echo '{'."code:3,status:\"undamaged\",turns:2,X:13,Y:3".'}'; echo",\n";
	echo '{'."code:61,status:\"undamaged\",turns:0,X:41,Y:4".'}'; echo",\n";
	echo '{'."code:91,status:\"undamaged\",turns:1,X:15,Y:5".'}'; echo",\n";
	echo '{'."code:111,status:\"undamaged\",turns:2,X:16,Y:6".'}'; echo",\n";
	echo '{'."code:141,status:\"undamaged\",turns:0,X:16,Y:7".'}'; echo",\n";
	echo '{'."code:151,status:\"undamaged\",turns:1,X:41,Y:8".'}'; echo",\n";
	echo '{'."code:161,status:\"undamaged\",turns:2,X:13,Y:9".'}'; echo",\n";
	echo '{'."code:171,status:\"undamaged\",turns:0,X:61,Y:10".'}';
	echo ']';
}

if($q == 4){
	//text case 4
	echo '[';
echo '{'."code:1,status:\"undamaged\",turns:0,X:1,Y:13".'}'; echo",\n";
echo '{'."code:2,status:\"undamaged\",turns:1,X:11,Y:2".'}'; echo",\n";
echo '{'."code:3,status:\"undamaged\",turns:2,X:12,Y:3".'}'; echo",\n";
echo '{'."code:61,status:\"undamaged\",turns:0,X:1,Y:34".'}'; echo",\n";
echo '{'."code:91,status:\"undamaged\",turns:1,X:1,Y:25".'}'; echo",\n";
echo '{'."code:111,status:\"undamaged\",turns:2,X:41,Y:6".'}'; echo",\n";
echo '{'."code:141,status:\"undamaged\",turns:0,X:1,Y:7".'}'; echo",\n";
echo '{'."code:151,status:\"undamaged\",turns:1,X:1,Y:18".'}'; echo",\n";
echo '{'."code:161,status:\"undamaged\",turns:2,X:61,Y:9".'}'; echo",\n";
echo '{'."code:171,status:\"undamaged\",turns:0,X:16,Y:10".'}';
	echo ']';
}

if($q == 5){
	//text case 5
	echo '[';
echo '{'."code:1,status:\"undamaged\",turns:0,X:1,Y:21".'}'; echo",\n";
echo '{'."code:2,status:\"undamaged\",turns:1,X:11,Y:2".'}'; echo",\n";
echo '{'."code:3,status:\"undamaged\",turns:2,X:1,Y:3".'}'; echo",\n";
echo '{'."code:61,status:\"undamaged\",turns:0,X:12,Y:42".'}'; echo",\n";
echo '{'."code:91,status:\"undamaged\",turns:1,X:1,Y:5".'}'; echo",\n";
echo '{'."code:111,status:\"undamaged\",turns:2,X:1,Y:6".'}'; echo",\n";
echo '{'."code:141,status:\"undamaged\",turns:0,X:11,Y:7".'}'; echo",\n";
echo '{'."code:151,status:\"undamaged\",turns:1,X:1,Y:18".'}'; echo",\n";
echo '{'."code:161,status:\"undamaged\",turns:2,X:1,Y:19".'}'; echo",\n";
echo '{'."code:171,status:\"undamaged\",turns:0,X:11,Y:10".'}';
	echo ']';
}

if($q == 6){
	//text case 6
	echo '[';
echo '{'."code:1,status:\"undamaged\",turns:0,X:21,Y:41".'}'; echo",\n";
echo '{'."code:2,status:\"undamaged\",turns:1,X:13,Y:22".'}'; echo",\n";
echo '{'."code:3,status:\"undamaged\",turns:2,X:1,Y:3".'}'; echo",\n";
echo '{'."code:61,status:\"undamaged\",turns:0,X:41,Y:4".'}'; echo",\n";
echo '{'."code:91,status:\"undamaged\",turns:1,X:11,Y:5".'}'; echo",\n";
echo '{'."code:111,status:\"undamaged\",turns:2,X:1,Y:6".'}'; echo",\n";
echo '{'."code:141,status:\"undamaged\",turns:0,X:1,Y:7".'}'; echo",\n";
echo '{'."code:151,status:\"undamaged\",turns:1,X:11,Y:28".'}'; echo",\n";
echo '{'."code:161,status:\"undamaged\",turns:2,X:14,Y:9".'}'; echo",\n";
echo '{'."code:171,status:\"undamaged\",turns:0,X:21,Y:15".'}';
	echo ']';
}


?>