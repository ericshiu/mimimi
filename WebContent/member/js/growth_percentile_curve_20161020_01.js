// JavaScript Document
console.log("import growth_percentile_curve(cur)");
/*取得生長曲線的參數
 */
function cur_get_percentile_arr_gender(str_gender){	
	var arr = new Array();
		if(str_gender == '_m'){
			arr.push(cur_get_percentile_arr_m03());
			arr.push(cur_get_percentile_arr_m15());
			arr.push(cur_get_percentile_arr_m50());
			arr.push(cur_get_percentile_arr_m85());
			arr.push(cur_get_percentile_arr_m97());
		}else{
			arr.push(cur_get_percentile_arr_f03());
			arr.push(cur_get_percentile_arr_f15());
			arr.push(cur_get_percentile_arr_f50());
			arr.push(cur_get_percentile_arr_f85());
			arr.push(cur_get_percentile_arr_f97());			
		}
		return arr;
}
/*取得生長曲線的參數-年齡(不分性別)
 */
function cur_get_percentile_arr_age(){
	var arr_age = new Array();		
	var arr_ageY = new Array();	//year array
	var arr_ageM = new Array();	//month array		
		arr_ageY.push(0);  arr_ageM.push(0);	//0
		arr_ageY.push(0);  arr_ageM.push(1);	//1
		arr_ageY.push(0);  arr_ageM.push(2);	//2
		arr_ageY.push(0);  arr_ageM.push(3);	//3
		arr_ageY.push(0);  arr_ageM.push(4);	//4
		arr_ageY.push(0);  arr_ageM.push(5);	//5
		arr_ageY.push(0);  arr_ageM.push(6);	//6
		arr_ageY.push(0);  arr_ageM.push(7);	//7
		arr_ageY.push(0);  arr_ageM.push(8);	//8
		arr_ageY.push(0);  arr_ageM.push(9);	//9
		arr_ageY.push(0);  arr_ageM.push(10);	//10
		arr_ageY.push(0);  arr_ageM.push(11);	//11
		arr_ageY.push(1);  arr_ageM.push(0);	//12
		arr_ageY.push(1);  arr_ageM.push(1);	//13
		arr_ageY.push(1);  arr_ageM.push(2);	//14
		arr_ageY.push(1);  arr_ageM.push(3);	//15
		arr_ageY.push(1);  arr_ageM.push(4);	//16
		arr_ageY.push(1);  arr_ageM.push(5);	//17
		arr_ageY.push(1);  arr_ageM.push(6);	//18
		arr_ageY.push(1);  arr_ageM.push(7);	//19
		arr_ageY.push(1);  arr_ageM.push(8);	//20
		arr_ageY.push(1);  arr_ageM.push(9);	//21
		arr_ageY.push(1);  arr_ageM.push(10);	//22
		arr_ageY.push(1);  arr_ageM.push(11);	//23
		arr_ageY.push(2);  arr_ageM.push(0);	//24
		arr_ageY.push(2);  arr_ageM.push(1);	//25
		arr_ageY.push(2);  arr_ageM.push(2);	//26
		arr_ageY.push(2);  arr_ageM.push(3);	//27
		arr_ageY.push(2);  arr_ageM.push(4);	//28
		arr_ageY.push(2);  arr_ageM.push(5);	//29
		arr_ageY.push(2);  arr_ageM.push(6);	//30
		arr_ageY.push(2);  arr_ageM.push(7);	//31
		arr_ageY.push(2);  arr_ageM.push(8);	//32
		arr_ageY.push(2);  arr_ageM.push(9);	//33
		arr_ageY.push(2);  arr_ageM.push(10);	//34
		arr_ageY.push(2);  arr_ageM.push(11);	//35
		arr_ageY.push(3);  arr_ageM.push(0);	//36
		arr_ageY.push(3);  arr_ageM.push(1);	//37
		arr_ageY.push(3);  arr_ageM.push(2);	//38
		arr_ageY.push(3);  arr_ageM.push(3);	//39
		arr_ageY.push(3);  arr_ageM.push(4);	//40
		arr_ageY.push(3);  arr_ageM.push(5);	//41
		arr_ageY.push(3);  arr_ageM.push(6);	//42
		arr_ageY.push(3);  arr_ageM.push(7);	//43
		arr_ageY.push(3);  arr_ageM.push(8);	//44
		arr_ageY.push(3);  arr_ageM.push(9);	//45
		arr_ageY.push(3);  arr_ageM.push(10);	//46
		arr_ageY.push(3);  arr_ageM.push(11);	//47
		arr_ageY.push(4);  arr_ageM.push(0);	//48
		arr_ageY.push(4);  arr_ageM.push(1);	//49
		arr_ageY.push(4);  arr_ageM.push(2);	//50
		arr_ageY.push(4);  arr_ageM.push(3);	//51
		arr_ageY.push(4);  arr_ageM.push(4);	//52
		arr_ageY.push(4);  arr_ageM.push(5);	//53
		arr_ageY.push(4);  arr_ageM.push(6);	//54
		arr_ageY.push(4);  arr_ageM.push(7);	//55
		arr_ageY.push(4);  arr_ageM.push(8);	//56
		arr_ageY.push(4);  arr_ageM.push(9);	//57
		arr_ageY.push(4);  arr_ageM.push(10);	//58
		arr_ageY.push(4);  arr_ageM.push(11);	//59
		arr_ageY.push(5);  arr_ageM.push(0);	//60
		arr_ageY.push(5);  arr_ageM.push(6);	//61
		arr_ageY.push(6);  arr_ageM.push(0);	//62
		arr_ageY.push(6);  arr_ageM.push(6);	//63
		arr_ageY.push(7);  arr_ageM.push(0);	//64
		arr_ageY.push(7);  arr_ageM.push(6);	//65
		arr_ageY.push(8);  arr_ageM.push(0);	//66
		arr_ageY.push(8);  arr_ageM.push(6);	//67
		arr_ageY.push(9);  arr_ageM.push(0);	//68
		arr_ageY.push(9);  arr_ageM.push(6);	//69
		arr_ageY.push(10); arr_ageM.push(0);	//70
		arr_ageY.push(10); arr_ageM.push(6);	//71
		arr_ageY.push(11); arr_ageM.push(0);	//72
		arr_ageY.push(11); arr_ageM.push(6);	//73
		arr_ageY.push(12); arr_ageM.push(0);	//74
		arr_ageY.push(12); arr_ageM.push(6);	//75
		arr_ageY.push(13); arr_ageM.push(0);	//76
		arr_ageY.push(13); arr_ageM.push(6);	//77
		arr_ageY.push(14); arr_ageM.push(0);	//78
		arr_ageY.push(14); arr_ageM.push(6);	//79
		arr_ageY.push(15); arr_ageM.push(0);	//80
		arr_ageY.push(15); arr_ageM.push(6);	//81
		arr_ageY.push(16); arr_ageM.push(0);	//82
		arr_ageY.push(16); arr_ageM.push(6);	//83
		arr_ageY.push(17); arr_ageM.push(0);	//84
		arr_ageY.push(17); arr_ageM.push(6);	//85
		arr_ageY.push(18); arr_ageM.push(0);	//86
		arr_ageY.push(18); arr_ageM.push(6);	//87		
		arr_age = age_get_percentile_array_year_month_old_to_decimal(arr_ageY, arr_ageM);//將年齡轉換成小數點		
		return arr_age;
}
/*取得生長曲線的參數
 */
function cur_get_percentile_arr_m03(){
	var arr_age = new Array();		arr_age = cur_get_percentile_arr_age();
	var arr_height = new Array();	var arr_weight = new Array();	var arr = new Array;	
		arr_height.push(46.3);	arr_weight.push(2.5);	//0
		arr_height.push(51);	arr_weight.push(3.4);	//1
		arr_height.push(54.5);	arr_weight.push(4.3);	//2
		arr_height.push(57.5);	arr_weight.push(5);		//3
		arr_height.push(60);	arr_weight.push(5.6);	//4
		arr_height.push(62);	arr_weight.push(6);		//5
		arr_height.push(63.6);	arr_weight.push(6.4);	//6
		arr_height.push(65);	arr_weight.push(6.8);	//7
		arr_height.push(66.5);	arr_weight.push(7);		//8
		arr_height.push(68);	arr_weight.push(7.2);	//9
		arr_height.push(69);	arr_weight.push(7.5);	//10
		arr_height.push(70);	arr_weight.push(7.7);	//11
		arr_height.push(71.3);	arr_weight.push(7.8);	//12
		arr_height.push(72);	arr_weight.push(8);		//13
		arr_height.push(73.5);	arr_weight.push(8.1);	//14
		arr_height.push(74.5);	arr_weight.push(8.4);	//15
		arr_height.push(75.5);	arr_weight.push(8.5);	//16
		arr_height.push(76);	arr_weight.push(8.8);	//17
		arr_height.push(77.2);	arr_weight.push(8.9);	//18
		arr_height.push(78);	arr_weight.push(9);		//19
		arr_height.push(79);	arr_weight.push(9.1);	//20
		arr_height.push(80);	arr_weight.push(9.3);	//21
		arr_height.push(80.5);	arr_weight.push(9.5);	//22
		arr_height.push(81.2);	arr_weight.push(9.6);	//23
		arr_height.push(82.1);	arr_weight.push(9.8);	//24
		arr_height.push(82.1);	arr_weight.push(10);	//25
		arr_height.push(83);	arr_weight.push(10.1);	//26
		arr_height.push(83.5);	arr_weight.push(10.2);	//27
		arr_height.push(84);	arr_weight.push(10.5);	//28
		arr_height.push(85);	arr_weight.push(10.6);	//29
		arr_height.push(85.5);	arr_weight.push(10.7);	//30
		arr_height.push(86);	arr_weight.push(10.9);	//31
		arr_height.push(87);	arr_weight.push(11);	//32
		arr_height.push(87.5);	arr_weight.push(11.1);	//33
		arr_height.push(88);	arr_weight.push(11.2);	//34
		arr_height.push(88.5);	arr_weight.push(11.3);	//35
		arr_height.push(89.1);	arr_weight.push(11.4);	//36
		arr_height.push(89.7);	arr_weight.push(11.6);	//37
		arr_height.push(90);	arr_weight.push(11.7);	//38
		arr_height.push(91);	arr_weight.push(11.8);	//39
		arr_height.push(91.5);	arr_weight.push(11.9);	//40
		arr_height.push(92);	arr_weight.push(12);	//41
		arr_height.push(92.4);	arr_weight.push(12.2);	//42
		arr_height.push(93);	arr_weight.push(12.3);	//43
		arr_height.push(93.5);	arr_weight.push(12.4);	//44
		arr_height.push(94);	arr_weight.push(12.5);	//45
		arr_height.push(94.5);	arr_weight.push(12.6);	//46
		arr_height.push(95);	arr_weight.push(12.8);	//47
		arr_height.push(95.4);	arr_weight.push(12.9);	//48
		arr_height.push(96);	arr_weight.push(13);	//49
		arr_height.push(96.5);	arr_weight.push(13.1);	//50
		arr_height.push(97);	arr_weight.push(13.2);	//51
		arr_height.push(97.5);	arr_weight.push(13.3);	//52
		arr_height.push(98);	arr_weight.push(13.5);	//53
		arr_height.push(98.4);	arr_weight.push(13.6);	//54
		arr_height.push(99);	arr_weight.push(13.7);	//55
		arr_height.push(99.5);	arr_weight.push(13.8);	//56
		arr_height.push(100);	arr_weight.push(13.9);	//57
		arr_height.push(100.1);	arr_weight.push(14);	//58
		arr_height.push(100.5);	arr_weight.push(14.1);	//59
		arr_height.push(101.2);	arr_weight.push(14.3);	//60
		arr_height.push(103.9);	arr_weight.push(15.3);	//61
		arr_height.push(106.5);	arr_weight.push(16.3);	//62
		arr_height.push(109.2);	arr_weight.push(17.4);	//63
		arr_height.push(111.8);	arr_weight.push(18.4);	//64
		arr_height.push(114.5);	arr_weight.push(19.4);	//65
		arr_height.push(117);	arr_weight.push(20.3);	//66
		arr_height.push(119.5);	arr_weight.push(21.2);	//67
		arr_height.push(121.8);	arr_weight.push(22.1);	//68
		arr_height.push(124);	arr_weight.push(23);	//69
		arr_height.push(126);	arr_weight.push(24);	//70
		arr_height.push(128);	arr_weight.push(25);	//71
		arr_height.push(130.5);	arr_weight.push(26.3);	//72
		arr_height.push(133);	arr_weight.push(27.6);	//73
		arr_height.push(135.6);	arr_weight.push(29.3);	//74
		arr_height.push(138.2);	arr_weight.push(30.5);	//75
		arr_height.push(141.9);	arr_weight.push(32.8);	//76
		arr_height.push(145.5);	arr_weight.push(35);	//77
		arr_height.push(149.3);	arr_weight.push(38);	//78
		arr_height.push(153);	arr_weight.push(41);	//79
		arr_height.push(155.5);	arr_weight.push(43);	//80
		arr_height.push(158);	arr_weight.push(45);	//81
		arr_height.push(159.3);	arr_weight.push(46.8);	//82
		arr_height.push(160.5);	arr_weight.push(48.5);	//83
		arr_height.push(160.9);	arr_weight.push(49.3);	//84
		arr_height.push(161);	arr_weight.push(50);	//85
		arr_height.push(161.5);	arr_weight.push(50.3);	//86
		arr_height.push(162);	arr_weight.push(50.5);	//87
		arr.push(arr_height);
		arr.push(arr_weight);
		return arr
}
/*取得生長曲線的參數
 */
function cur_get_percentile_arr_m15(){
	var arr_age = new Array();		arr_age = cur_get_percentile_arr_age();
	var arr_height = new Array();	var arr_weight = new Array();	var arr = new Array;	
		arr_height.push(47.9);	arr_weight.push(2.9);	//0
		arr_height.push(53);	arr_weight.push(3.8);	//1
		arr_height.push(56.5);	arr_weight.push(4.9);	//2
		arr_height.push(59);	arr_weight.push(5.5);	//3
		arr_height.push(62);	arr_weight.push(6.2);	//4
		arr_height.push(63.5);	arr_weight.push(6.7);	//5
		arr_height.push(65.4);	arr_weight.push(7.1);	//6
		arr_height.push(67);	arr_weight.push(7.4);	//7
		arr_height.push(68);	arr_weight.push(7.7);	//8
		arr_height.push(69.5);	arr_weight.push(8);		//9
		arr_height.push(71);	arr_weight.push(8.1);	//10
		arr_height.push(72);	arr_weight.push(8.4);	//11
		arr_height.push(73.3);	arr_weight.push(8.6);	//12
		arr_height.push(74.5);	arr_weight.push(8.9);	//13
		arr_height.push(75.5);	arr_weight.push(9);		//14
		arr_height.push(76.5);	arr_weight.push(9.1);	//15
		arr_height.push(77.5);	arr_weight.push(9.4);	//16
		arr_height.push(78.5);	arr_weight.push(9.6);	//17
		arr_height.push(79.5);	arr_weight.push(9.7);	//18
		arr_height.push(80);	arr_weight.push(9.9);	//19
		arr_height.push(81);	arr_weight.push(10);	//20
		arr_height.push(82);	arr_weight.push(10.2);	//21
		arr_height.push(83);	arr_weight.push(10.5);	//22
		arr_height.push(84);	arr_weight.push(10.7);	//23
		arr_height.push(84.6);	arr_weight.push(10.8);	//24
		arr_height.push(84.6);	arr_weight.push(11);	//25
		arr_height.push(85.5);	arr_weight.push(11.1);	//26
		arr_height.push(86);	arr_weight.push(11.3);	//27
		arr_height.push(87);	arr_weight.push(11.5);	//28
		arr_height.push(88);	arr_weight.push(11.6);	//29
		arr_height.push(88.4);	arr_weight.push(11.8);	//30
		arr_height.push(89);	arr_weight.push(12);	//31
		arr_height.push(90);	arr_weight.push(12.1);	//32
		arr_height.push(90.5);	arr_weight.push(12.2);	//33
		arr_height.push(91);	arr_weight.push(12.4);	//34
		arr_height.push(91.5);	arr_weight.push(12.5);	//35
		arr_height.push(92.2);	arr_weight.push(12.7);	//36
		arr_height.push(93);	arr_weight.push(12.8);	//37
		arr_height.push(93.5);	arr_weight.push(13);	//38
		arr_height.push(94);	arr_weight.push(13.1);	//39
		arr_height.push(94.5);	arr_weight.push(13.2);	//40
		arr_height.push(95);	arr_weight.push(13.4);	//41
		arr_height.push(95.7);	arr_weight.push(13.5);	//42
		arr_height.push(96);	arr_weight.push(13.7);	//43
		arr_height.push(97);	arr_weight.push(13.9);	//44
		arr_height.push(97.5);	arr_weight.push(14);	//45
		arr_height.push(98);	arr_weight.push(14.1);	//46
		arr_height.push(98.5);	arr_weight.push(14.2);	//47
		arr_height.push(99);	arr_weight.push(14.3);	//48
		arr_height.push(99.5);	arr_weight.push(14.5);	//49
		arr_height.push(100);	arr_weight.push(14.6);	//50
		arr_height.push(100.5);	arr_weight.push(14.8);	//51
		arr_height.push(101);	arr_weight.push(14.9);	//52
		arr_height.push(101.5);	arr_weight.push(15);	//53
		arr_height.push(102.1);	arr_weight.push(15.2);	//54
		arr_height.push(102.5);	arr_weight.push(15.2);	//55
		arr_height.push(103);	arr_weight.push(15.4);	//56
		arr_height.push(103.5);	arr_weight.push(15.6);	//57
		arr_height.push(104);	arr_weight.push(15.7);	//58
		arr_height.push(104.5);	arr_weight.push(15.9);	//59
		arr_height.push(105.2);	arr_weight.push(16);	//60
		arr_height.push(107.9);	arr_weight.push(17.1);	//61
		arr_height.push(110.5);	arr_weight.push(18.2);	//62
		arr_height.push(113.2);	arr_weight.push(19.3);	//63
		arr_height.push(115.8);	arr_weight.push(20.4);	//64
		arr_height.push(118.5);	arr_weight.push(21.5);	//65
		arr_height.push(121.3);	arr_weight.push(22.7);	//66
		arr_height.push(124);	arr_weight.push(23.8);	//67
		arr_height.push(126);	arr_weight.push(24.8);	//68
		arr_height.push(128);	arr_weight.push(25.8);	//69
		arr_height.push(130.5);	arr_weight.push(26.9);	//70
		arr_height.push(133);	arr_weight.push(28);	//71
		arr_height.push(135.6);	arr_weight.push(29.6);	//72
		arr_height.push(138.1);	arr_weight.push(31.2);	//73
		arr_height.push(141.1);	arr_weight.push(33.1);	//74
		arr_height.push(144);	arr_weight.push(35);	//75
		arr_height.push(148.5);	arr_weight.push(38);	//76
		arr_height.push(153);	arr_weight.push(41);	//77
		arr_height.push(156.3);	arr_weight.push(44);	//78
		arr_height.push(159.6);	arr_weight.push(47);	//79
		arr_height.push(161.3);	arr_weight.push(49);	//80
		arr_height.push(163);	arr_weight.push(51);	//81
		arr_height.push(164);	arr_weight.push(52);	//82
		arr_height.push(165);	arr_weight.push(53);	//83
		arr_height.push(165.5);	arr_weight.push(54);	//84
		arr_height.push(166);	arr_weight.push(55);	//85
		arr_height.push(166);	arr_weight.push(55);	//86
		arr_height.push(166);	arr_weight.push(55);	//87
		arr.push(arr_height);
		arr.push(arr_weight);
		return arr
}
/*取得生長曲線的參數
 */
function cur_get_percentile_arr_m50(){
	var arr_age = new Array();		arr_age = cur_get_percentile_arr_age();
	var arr_height = new Array();	var arr_weight = new Array();	var arr = new Array;	
		arr_height.push(49.9);	arr_weight.push(3.3);	//0
		arr_height.push(55);	arr_weight.push(4.5);	//1
		arr_height.push(58.5);	arr_weight.push(5.5);	//2
		arr_height.push(61.5);	arr_weight.push(6.4);	//3
		arr_height.push(64);	arr_weight.push(7);		//4
		arr_height.push(66);	arr_weight.push(7.5);	//5
		arr_height.push(67.6);	arr_weight.push(7.9);	//6
		arr_height.push(69);	arr_weight.push(8.3);	//7
		arr_height.push(70.5);	arr_weight.push(8.6);	//8
		arr_height.push(72);	arr_weight.push(8.9);	//9
		arr_height.push(73);	arr_weight.push(9.1);	//10
		arr_height.push(74.5);	arr_weight.push(9.4);	//11
		arr_height.push(75.7);	arr_weight.push(9.6);	//12
		arr_height.push(77);	arr_weight.push(9.9);	//13
		arr_height.push(78);	arr_weight.push(10);	//14
		arr_height.push(79);	arr_weight.push(10.3);	//15
		arr_height.push(80);	arr_weight.push(10.6);	//16
		arr_height.push(81);	arr_weight.push(10.8);	//17
		arr_height.push(82.3);	arr_weight.push(10.9);	//18
		arr_height.push(83);	arr_weight.push(11.1);	//19
		arr_height.push(84);	arr_weight.push(11.4);	//20
		arr_height.push(85);	arr_weight.push(11.6);	//21
		arr_height.push(86);	arr_weight.push(11.7);	//22
		arr_height.push(87);	arr_weight.push(12);	//23
		arr_height.push(87.8);	arr_weight.push(12.2);	//24
		arr_height.push(88);	arr_weight.push(12.4);	//25
		arr_height.push(89);	arr_weight.push(12.6);	//26
		arr_height.push(89.5);	arr_weight.push(12.9);	//27
		arr_height.push(90.5);	arr_weight.push(13);	//28
		arr_height.push(91);	arr_weight.push(13.1);	//29
		arr_height.push(91.9);	arr_weight.push(13.3);	//30
		arr_height.push(93);	arr_weight.push(13.5);	//31
		arr_height.push(93.5);	arr_weight.push(13.6);	//32
		arr_height.push(94);	arr_weight.push(13.9);	//33
		arr_height.push(95);	arr_weight.push(14);	//34
		arr_height.push(95.5);	arr_weight.push(14.1);	//35
		arr_height.push(96.1);	arr_weight.push(14.3);	//36
		arr_height.push(97);	arr_weight.push(14.5);	//37
		arr_height.push(97.5);	arr_weight.push(14.7);	//38
		arr_height.push(98);	arr_weight.push(14.9);	//39
		arr_height.push(98.5);	arr_weight.push(15);	//40
		arr_height.push(99);	arr_weight.push(15.1);	//41
		arr_height.push(99.9);	arr_weight.push(15.3);	//42
		arr_height.push(100.5);	arr_weight.push(15.5);	//43
		arr_height.push(101);	arr_weight.push(15.7);	//44
		arr_height.push(101.5);	arr_weight.push(15.9);	//45
		arr_height.push(102);	arr_weight.push(16);	//46
		arr_height.push(103);	arr_weight.push(16.1);	//47
		arr_height.push(103.5);	arr_weight.push(16.3);	//48
		arr_height.push(104);	arr_weight.push(16.5);	//49
		arr_height.push(104.5);	arr_weight.push(16.7);	//50
		arr_height.push(105);	arr_weight.push(16.9);	//51
		arr_height.push(105.5);	arr_weight.push(17);	//52
		arr_height.push(106);	arr_weight.push(17.1);	//53
		arr_height.push(106.7);	arr_weight.push(17.3);	//54
		arr_height.push(107.1);	arr_weight.push(17.5);	//55
		arr_height.push(108);	arr_weight.push(17.7);	//56
		arr_height.push(108.5);	arr_weight.push(17.9);	//57
		arr_height.push(109);	arr_weight.push(18);	//58
		arr_height.push(109.5);	arr_weight.push(18.1);	//59
		arr_height.push(110);	arr_weight.push(18.3);	//60
		arr_height.push(112.8);	arr_weight.push(19.6);	//61
		arr_height.push(115.6);	arr_weight.push(20.9);	//62
		arr_height.push(118.4);	arr_weight.push(22.3);	//63
		arr_height.push(121.2);	arr_weight.push(23.6);	//64
		arr_height.push(124);	arr_weight.push(24.9);	//65
		arr_height.push(126.8);	arr_weight.push(26.3);	//66
		arr_height.push(129.5);	arr_weight.push(27.6);	//67
		arr_height.push(131.8);	arr_weight.push(28.8);	//68
		arr_height.push(134);	arr_weight.push(30);	//69
		arr_height.push(136.5);	arr_weight.push(31.5);	//70
		arr_height.push(139);	arr_weight.push(33);	//71
		arr_height.push(142);	arr_weight.push(35.3);	//72
		arr_height.push(145);	arr_weight.push(37.6);	//73
		arr_height.push(148.8);	arr_weight.push(40.3);	//74
		arr_height.push(152.5);	arr_weight.push(43);	//75
		arr_height.push(156.9);	arr_weight.push(46.5);	//76
		arr_height.push(161.2);	arr_weight.push(50);	//77
		arr_height.push(163.7);	arr_weight.push(52.5);	//78
		arr_height.push(166.2);	arr_weight.push(54.9);	//79
		arr_height.push(167.6);	arr_weight.push(56.5);	//80
		arr_height.push(169);	arr_weight.push(58);	//81
		arr_height.push(170);	arr_weight.push(59);	//82
		arr_height.push(171);	arr_weight.push(60);	//83
		arr_height.push(171.5);	arr_weight.push(61);	//84
		arr_height.push(172);	arr_weight.push(62);	//85
		arr_height.push(172);	arr_weight.push(62.5);	//86
		arr_height.push(172);	arr_weight.push(63);	//87		
		arr.push(arr_height);
		arr.push(arr_weight);
		return arr
}
/*取得生長曲線的參數
 */
function cur_get_percentile_arr_m85(){
	var arr_age = new Array();		arr_age = cur_get_percentile_arr_age();
	var arr_height = new Array();	var arr_weight = new Array();	var arr = new Array;	
		arr_height.push(51.8);	arr_weight.push(3.9);	//0
		arr_height.push(57);	arr_weight.push(5);		//1
		arr_height.push(60);	arr_weight.push(6.3);	//2
		arr_height.push(63.5);	arr_weight.push(7.1);	//3
		arr_height.push(66);	arr_weight.push(7.9);	//4
		arr_height.push(68);	arr_weight.push(8.4);	//5
		arr_height.push(69.8);	arr_weight.push(8.9);	//6
		arr_height.push(71.5);	arr_weight.push(9.2);	//7
		arr_height.push(73);	arr_weight.push(9.6);	//8
		arr_height.push(74);	arr_weight.push(10);	//9
		arr_height.push(75.5);	arr_weight.push(10.3);	//10
		arr_height.push(77);	arr_weight.push(10.5);	//11
		arr_height.push(78.2);	arr_weight.push(10.8);	//12
		arr_height.push(79.5);	arr_weight.push(11);	//13
		arr_height.push(80.5);	arr_weight.push(11.2);	//14
		arr_height.push(81.5);	arr_weight.push(11.6);	//15
		arr_height.push(83);	arr_weight.push(11.9);	//16
		arr_height.push(84);	arr_weight.push(12);	//17
		arr_height.push(85.1);	arr_weight.push(12.3);	//18
		arr_height.push(86);	arr_weight.push(12.5);	//19
		arr_height.push(87);	arr_weight.push(12.8);	//20
		arr_height.push(88);	arr_weight.push(13);	//21
		arr_height.push(89);	arr_weight.push(13.1);	//22
		arr_height.push(90);	arr_weight.push(13.4);	//23
		arr_height.push(91);	arr_weight.push(13.7);	//24
		arr_height.push(91.2);	arr_weight.push(13.9);	//25
		arr_height.push(92);	arr_weight.push(14.1);	//26
		arr_height.push(93);	arr_weight.push(14.4);	//27
		arr_height.push(94);	arr_weight.push(14.5);	//28
		arr_height.push(94.5);	arr_weight.push(14.8);	//29
		arr_height.push(95.5);	arr_weight.push(15);	//30
		arr_height.push(96);	arr_weight.push(15.2);	//31
		arr_height.push(97);	arr_weight.push(15.4);	//32
		arr_height.push(98);	arr_weight.push(15.6);	//33
		arr_height.push(98.5);	arr_weight.push(15.9);	//34
		arr_height.push(99);	arr_weight.push(16);	//35
		arr_height.push(99.9);	arr_weight.push(16.3);	//36
		arr_height.push(100.5);	arr_weight.push(16.4);	//37
		arr_height.push(101);	arr_weight.push(16.6);	//38
		arr_height.push(102);	arr_weight.push(16.9);	//39
		arr_height.push(102.5);	arr_weight.push(17);	//40
		arr_height.push(103.5);	arr_weight.push(17.2);	//41
		arr_height.push(104);	arr_weight.push(17.5);	//42
		arr_height.push(104.5);	arr_weight.push(17.7);	//43
		arr_height.push(105);	arr_weight.push(17.9);	//44
		arr_height.push(106);	arr_weight.push(18);	//45
		arr_height.push(106.5);	arr_weight.push(18.2);	//46
		arr_height.push(107);	arr_weight.push(18.5);	//47
		arr_height.push(107.7);	arr_weight.push(18.7);	//48
		arr_height.push(108.5);	arr_weight.push(18.9);	//49
		arr_height.push(109);	arr_weight.push(19);	//50
		arr_height.push(109.5);	arr_weight.push(19.2);	//51
		arr_height.push(110);	arr_weight.push(19.5);	//52
		arr_height.push(110.5);	arr_weight.push(19.7);	//53
		arr_height.push(111.2);	arr_weight.push(19.9);	//54
		arr_height.push(112);	arr_weight.push(20);	//55
		arr_height.push(112.5);	arr_weight.push(20.2);	//56
		arr_height.push(113);	arr_weight.push(20.5);	//57
		arr_height.push(113.5);	arr_weight.push(20.7);	//58
		arr_height.push(114);	arr_weight.push(20.9);	//59
		arr_height.push(114.8);	arr_weight.push(21.1);	//60
		arr_height.push(117.7);	arr_weight.push(22.9);	//61
		arr_height.push(120.6);	arr_weight.push(24.7);	//62
		arr_height.push(123.6);	arr_weight.push(26.4);	//63
		arr_height.push(126.5);	arr_weight.push(28.2);	//64
		arr_height.push(129.4);	arr_weight.push(30);	//65
		arr_height.push(132.2);	arr_weight.push(32.2);	//66
		arr_height.push(135);	arr_weight.push(34.3);	//67
		arr_height.push(137.5);	arr_weight.push(35.7);	//68
		arr_height.push(140);	arr_weight.push(37);	//69
		arr_height.push(142.8);	arr_weight.push(39.4);	//70
		arr_height.push(145.5);	arr_weight.push(41.8);	//71
		arr_height.push(149.4);	arr_weight.push(44.7);	//72
		arr_height.push(153.2);	arr_weight.push(47.5);	//73
		arr_height.push(157.1);	arr_weight.push(50.4);	//74
		arr_height.push(161);	arr_weight.push(53.2);	//75
		arr_height.push(164.9);	arr_weight.push(56.8);	//76
		arr_height.push(168.7);	arr_weight.push(60.4);	//77
		arr_height.push(170.8);	arr_weight.push(62.7);	//78
		arr_height.push(172.8);	arr_weight.push(65);	//79
		arr_height.push(173.9);	arr_weight.push(66.5);	//80
		arr_height.push(175);	arr_weight.push(68);	//81
		arr_height.push(175.8);	arr_weight.push(69);	//82
		arr_height.push(176.5);	arr_weight.push(70);	//83
		arr_height.push(176.8);	arr_weight.push(70);	//84
		arr_height.push(177);	arr_weight.push(70);	//85
		arr_height.push(177.3);	arr_weight.push(70.5);	//86
		arr_height.push(177.5);	arr_weight.push(71);	//87		
		arr.push(arr_height);
		arr.push(arr_weight);
		return arr
}
/*取得生長曲線的參數
 */
function cur_get_percentile_arr_m97(){
	var arr_age = new Array();		arr_age = cur_get_percentile_arr_age();
	var arr_height = new Array();	var arr_weight = new Array();	var arr = new Array;
		arr_height.push(53.4);	arr_weight.push(4.3);	//0
		arr_height.push(58.5);	arr_weight.push(5.6);	//1
		arr_height.push(62);	arr_weight.push(7);		//2
		arr_height.push(65);	arr_weight.push(8);		//3
		arr_height.push(68);	arr_weight.push(8.5);	//4
		arr_height.push(70);	arr_weight.push(9.1);	//5
		arr_height.push(71.6);	arr_weight.push(9.7);	//6
		arr_height.push(73);	arr_weight.push(10.1);	//7
		arr_height.push(75);	arr_weight.push(10.6);	//8
		arr_height.push(76);	arr_weight.push(10.9);	//9
		arr_height.push(77.5);	arr_weight.push(11.2);	//10
		arr_height.push(79);	arr_weight.push(11.5);	//11
		arr_height.push(80.2);	arr_weight.push(11.8);	//12
		arr_height.push(81.5);	arr_weight.push(12);	//13
		arr_height.push(83);	arr_weight.push(12.4);	//14
		arr_height.push(84);	arr_weight.push(12.7);	//15
		arr_height.push(85);	arr_weight.push(13);	//16
		arr_height.push(86);	arr_weight.push(13.1);	//17
		arr_height.push(87.3);	arr_weight.push(13.5);	//18
		arr_height.push(88.5);	arr_weight.push(13.7);	//19
		arr_height.push(89.5);	arr_weight.push(14);	//20
		arr_height.push(90.5);	arr_weight.push(14.2);	//21
		arr_height.push(91.5);	arr_weight.push(14.6);	//22
		arr_height.push(92.5);	arr_weight.push(14.8);	//23
		arr_height.push(93.6);	arr_weight.push(15.1);	//24
		arr_height.push(94);	arr_weight.push(15.4);	//25
		arr_height.push(95);	arr_weight.push(15.6);	//26
		arr_height.push(95.5);	arr_weight.push(15.9);	//27
		arr_height.push(96.5);	arr_weight.push(16.1);	//28
		arr_height.push(97.5);	arr_weight.push(16.4);	//29
		arr_height.push(98.3);	arr_weight.push(16.6);	//30
		arr_height.push(99);	arr_weight.push(16.9);	//31
		arr_height.push(100);	arr_weight.push(17);	//32
		arr_height.push(101);	arr_weight.push(17.3);	//33
		arr_height.push(101.5);	arr_weight.push(17.5);	//34
		arr_height.push(102);	arr_weight.push(17.9);	//35
		arr_height.push(103.1);	arr_weight.push(18);	//36
		arr_height.push(104);	arr_weight.push(18.2);	//37
		arr_height.push(104.5);	arr_weight.push(18.5);	//38
		arr_height.push(105);	arr_weight.push(18.7);	//39
		arr_height.push(106);	arr_weight.push(19);	//40
		arr_height.push(106.5);	arr_weight.push(19.1);	//41
		arr_height.push(107.3);	arr_weight.push(19.4);	//42
		arr_height.push(108);	arr_weight.push(19.7);	//43
		arr_height.push(108.5);	arr_weight.push(19.9);	//44
		arr_height.push(109);	arr_weight.push(20.1);	//45
		arr_height.push(110);	arr_weight.push(20.4);	//46
		arr_height.push(110.5);	arr_weight.push(20.6);	//47
		arr_height.push(111.2);	arr_weight.push(20.9);	//48
		arr_height.push(112);	arr_weight.push(21);	//49
		arr_height.push(112.5);	arr_weight.push(21.3);	//50
		arr_height.push(113);	arr_weight.push(21.5);	//51
		arr_height.push(114);	arr_weight.push(21.9);	//52
		arr_height.push(114.5);	arr_weight.push(22);	//53
		arr_height.push(115);	arr_weight.push(22.3);	//54
		arr_height.push(115.5);	arr_weight.push(22.5);	//55
		arr_height.push(116);	arr_weight.push(22.8);	//56
		arr_height.push(117);	arr_weight.push(23);	//57
		arr_height.push(117.5);	arr_weight.push(23.2);	//58
		arr_height.push(118);	arr_weight.push(23.5);	//59
		arr_height.push(118.7);	arr_weight.push(23.8);	//60
		arr_height.push(121.8);	arr_weight.push(26.5);	//61
		arr_height.push(124.9);	arr_weight.push(29.2);	//62
		arr_height.push(128.1);	arr_weight.push(32);	//63
		arr_height.push(131.2);	arr_weight.push(34.7);	//64
		arr_height.push(134.3);	arr_weight.push(37.4);	//65
		arr_height.push(137.2);	arr_weight.push(40.2);	//66
		arr_height.push(140);	arr_weight.push(42.3);	//67
		arr_height.push(142.5);	arr_weight.push(44.3);	//68
		arr_height.push(145);	arr_weight.push(45.6);	//69
		arr_height.push(148.3);	arr_weight.push(48.6);	//70
		arr_height.push(151.5);	arr_weight.push(51.6);	//71
		arr_height.push(156.1);	arr_weight.push(54.8);	//72
		arr_height.push(160.7);	arr_weight.push(58);	//73
		arr_height.push(164.4);	arr_weight.push(61.5);	//74
		arr_height.push(168);	arr_weight.push(65);	//75
		arr_height.push(171);	arr_weight.push(68.5);	//76
		arr_height.push(174);	arr_weight.push(72);	//77
		arr_height.push(176);	arr_weight.push(74.3);	//78
		arr_height.push(178);	arr_weight.push(76.6);	//79
		arr_height.push(179);	arr_weight.push(77.6);	//80
		arr_height.push(180);	arr_weight.push(78.5);	//81
		arr_height.push(180.5);	arr_weight.push(79.3);	//82
		arr_height.push(181);	arr_weight.push(80);	//83
		arr_height.push(181.5);	arr_weight.push(80);	//84
		arr_height.push(182);	arr_weight.push(80);	//85
		arr_height.push(182);	arr_weight.push(80);	//86
		arr_height.push(182);	arr_weight.push(80);	//87		
		arr.push(arr_height);
		arr.push(arr_weight);
		return arr
}
/*取得生長曲線的參數
 */
function cur_get_percentile_arr_f03(){
	var arr_age = new Array();		arr_age = cur_get_percentile_arr_age();
	var arr_height = new Array();	var arr_weight = new Array();	var arr = new Array;	
		arr_height.push(45.6);	arr_weight.push(2.4);	//0
		arr_height.push(50);	arr_weight.push(3.1);	//1
		arr_height.push(53);	arr_weight.push(4);		//2
		arr_height.push(56);	arr_weight.push(4.6);	//3
		arr_height.push(58);	arr_weight.push(5);		//4
		arr_height.push(60);	arr_weight.push(5.5);	//5
		arr_height.push(61.5);	arr_weight.push(5.8);	//6
		arr_height.push(63);	arr_weight.push(6);		//7
		arr_height.push(64.3);	arr_weight.push(6.3);	//8
		arr_height.push(65.5);	arr_weight.push(6.5);	//9
		arr_height.push(67);	arr_weight.push(6.7);	//10
		arr_height.push(68);	arr_weight.push(7);		//11
		arr_height.push(69.2);	arr_weight.push(7.1);	//12
		arr_height.push(70);	arr_weight.push(7.3);	//13
		arr_height.push(71.5);	arr_weight.push(7.5);	//14
		arr_height.push(72.5);	arr_weight.push(7.6);	//15
		arr_height.push(73);	arr_weight.push(7.8);	//16
		arr_height.push(74.5);	arr_weight.push(8);		//17
		arr_height.push(75.2);	arr_weight.push(8.2);	//18
		arr_height.push(76);	arr_weight.push(8.3);	//19
		arr_height.push(77);	arr_weight.push(8.5);	//20
		arr_height.push(78);	arr_weight.push(8.6);	//21
		arr_height.push(79);	arr_weight.push(8.8);	//22
		arr_height.push(79.5);	arr_weight.push(9);		//23
		arr_height.push(80.3);	arr_weight.push(9.2);	//24
		arr_height.push(80.5);	arr_weight.push(9.3);	//25
		arr_height.push(81);	arr_weight.push(9.4);	//26
		arr_height.push(82);	arr_weight.push(9.6);	//27
		arr_height.push(83);	arr_weight.push(9.8);	//28
		arr_height.push(83.5);	arr_weight.push(9.9);	//29
		arr_height.push(84);	arr_weight.push(10.1);	//30
		arr_height.push(84.9);	arr_weight.push(10.2);	//31
		arr_height.push(85.5);	arr_weight.push(10.4);	//32
		arr_height.push(86);	arr_weight.push(10.5);	//33
		arr_height.push(86.9);	arr_weight.push(10.6);	//34
		arr_height.push(87.2);	arr_weight.push(10.8);	//35
		arr_height.push(87.9);	arr_weight.push(11);	//36
		arr_height.push(88.5);	arr_weight.push(11.1);	//37
		arr_height.push(89);	arr_weight.push(11.2);	//38
		arr_height.push(89.9);	arr_weight.push(11.3);	//39
		arr_height.push(90.2);	arr_weight.push(11.5);	//40
		arr_height.push(91);	arr_weight.push(11.6);	//41
		arr_height.push(91.4);	arr_weight.push(11.8);	//42
		arr_height.push(92);	arr_weight.push(11.9);	//43
		arr_height.push(92.5);	arr_weight.push(12);	//44
		arr_height.push(93);	arr_weight.push(12.1);	//45
		arr_height.push(93.7);	arr_weight.push(12.2);	//46
		arr_height.push(94);	arr_weight.push(12.4);	//47
		arr_height.push(94.6);	arr_weight.push(12.5);	//48
		arr_height.push(95);	arr_weight.push(12.6);	//49
		arr_height.push(95.4);	arr_weight.push(12.8);	//50
		arr_height.push(96);	arr_weight.push(12.9);	//51
		arr_height.push(96.9);	arr_weight.push(13);	//52
		arr_height.push(97);	arr_weight.push(13.1);	//53
		arr_height.push(97.6);	arr_weight.push(13.2);	//54
		arr_height.push(98);	arr_weight.push(13.4);	//55
		arr_height.push(98.7);	arr_weight.push(13.5);	//56
		arr_height.push(99);	arr_weight.push(13.6);	//57
		arr_height.push(99.6);	arr_weight.push(13.7);	//58
		arr_height.push(100);	arr_weight.push(13.8);	//59
		arr_height.push(100.5);	arr_weight.push(14);	//60
		arr_height.push(103);	arr_weight.push(14.9);	//61
		arr_height.push(105.5);	arr_weight.push(15.9);	//62
		arr_height.push(108.1);	arr_weight.push(16.8);	//63
		arr_height.push(110.6);	arr_weight.push(17.8);	//64
		arr_height.push(113.1);	arr_weight.push(18.7);	//65
		arr_height.push(115.7);	arr_weight.push(19.6);	//66
		arr_height.push(118.3);	arr_weight.push(20.4);	//67
		arr_height.push(120.7);	arr_weight.push(21.5);	//68
		arr_height.push(123);	arr_weight.push(22.5);	//69
		arr_height.push(125.8);	arr_weight.push(23.8);	//70
		arr_height.push(128.5);	arr_weight.push(25);	//71
		arr_height.push(131.8);	arr_weight.push(26.5);	//72
		arr_height.push(135);	arr_weight.push(28);	//73
		arr_height.push(137.9);	arr_weight.push(29.8);	//74
		arr_height.push(140.8);	arr_weight.push(31.5);	//75
		arr_height.push(143.2);	arr_weight.push(33.5);	//76
		arr_height.push(145.5);	arr_weight.push(35.5);	//77
		arr_height.push(146.8);	arr_weight.push(37.1);	//78
		arr_height.push(148);	arr_weight.push(38.6);	//79
		arr_height.push(148.5);	arr_weight.push(39.3);	//80
		arr_height.push(149);	arr_weight.push(40);	//81
		arr_height.push(149.5);	arr_weight.push(40.5);	//82
		arr_height.push(150);	arr_weight.push(41);	//83
		arr_height.push(150);	arr_weight.push(41.5);	//84
		arr_height.push(150);	arr_weight.push(42);	//85
		arr_height.push(150);	arr_weight.push(42);	//86
		arr_height.push(150);	arr_weight.push(42);	//87		
		arr.push(arr_height);
		arr.push(arr_weight);
		return arr
}
/*取得生長曲線的參數
 */
function cur_get_percentile_arr_f15(){
	var arr_age = new Array();		arr_age = cur_get_percentile_arr_age();
	var arr_height = new Array();	var arr_weight = new Array();	var arr = new Array;	
		arr_height.push(47.2);	arr_weight.push(2.8);	//0
		arr_height.push(52);	arr_weight.push(3.5);	//1
		arr_height.push(55);	arr_weight.push(4.4);	//2
		arr_height.push(57.5);	arr_weight.push(5);		//3
		arr_height.push(60);	arr_weight.push(5.7);	//4
		arr_height.push(62);	arr_weight.push(6);		//5
		arr_height.push(63.4);	arr_weight.push(6.4);	//6
		arr_height.push(65);	arr_weight.push(6.7);	//7
		arr_height.push(66.3);	arr_weight.push(7);		//8
		arr_height.push(68);	arr_weight.push(7.3);	//9
		arr_height.push(69);	arr_weight.push(7.5);	//10
		arr_height.push(70);	arr_weight.push(7.7);	//11
		arr_height.push(71.3);	arr_weight.push(7.9);	//12
		arr_height.push(72.5);	arr_weight.push(8);		//13
		arr_height.push(73.5);	arr_weight.push(8.2);	//14
		arr_height.push(75);	arr_weight.push(8.4);	//15
		arr_height.push(76);	arr_weight.push(8.6);	//16
		arr_height.push(77);	arr_weight.push(8.8);	//17
		arr_height.push(77.7);	arr_weight.push(9);		//18
		arr_height.push(78.5);	arr_weight.push(9.2);	//19
		arr_height.push(79.8);	arr_weight.push(9.4);	//20
		arr_height.push(80.5);	arr_weight.push(9.5);	//21
		arr_height.push(81.5);	arr_weight.push(9.7);	//22
		arr_height.push(82);	arr_weight.push(9.9);	//23
		arr_height.push(83.1);	arr_weight.push(10.1);	//24
		arr_height.push(83.2);	arr_weight.push(10.3);	//25
		arr_height.push(84);	arr_weight.push(10.4);	//26
		arr_height.push(85);	arr_weight.push(10.6);	//27
		arr_height.push(85.4);	arr_weight.push(10.9);	//28
		arr_height.push(86.2);	arr_weight.push(11);	//29
		arr_height.push(87);	arr_weight.push(11.2);	//30
		arr_height.push(87.9);	arr_weight.push(11.3);	//31
		arr_height.push(88.5);	arr_weight.push(11.5);	//32
		arr_height.push(89);	arr_weight.push(11.6);	//33
		arr_height.push(90);	arr_weight.push(11.8);	//34
		arr_height.push(90.5);	arr_weight.push(12);	//35
		arr_height.push(91.1);	arr_weight.push(12.1);	//36
		arr_height.push(91.8);	arr_weight.push(12.3);	//37
		arr_height.push(92.2);	arr_weight.push(12.4);	//38
		arr_height.push(93);	arr_weight.push(12.6);	//39
		arr_height.push(93.8);	arr_weight.push(12.8);	//40
		arr_height.push(94);	arr_weight.push(12.9);	//41
		arr_height.push(94.8);	arr_weight.push(13.1);	//42
		arr_height.push(95.5);	arr_weight.push(13.2);	//43
		arr_height.push(96);	arr_weight.push(13.4);	//44
		arr_height.push(96.7);	arr_weight.push(13.5);	//45
		arr_height.push(97);	arr_weight.push(13.6);	//46
		arr_height.push(97.8);	arr_weight.push(13.8);	//47
		arr_height.push(98.3);	arr_weight.push(14);	//48
		arr_height.push(99);	arr_weight.push(14.1);	//49
		arr_height.push(99.5);	arr_weight.push(14.3);	//50
		arr_height.push(100);	arr_weight.push(14.4);	//51
		arr_height.push(100.5);	arr_weight.push(14.6);	//52
		arr_height.push(101);	arr_weight.push(14.7);	//53
		arr_height.push(101.5);	arr_weight.push(14.8);	//54
		arr_height.push(102);	arr_weight.push(15);	//55
		arr_height.push(102.5);	arr_weight.push(15.1);	//56
		arr_height.push(103);	arr_weight.push(15.2);	//57
		arr_height.push(103.5);	arr_weight.push(15.4);	//58
		arr_height.push(104);	arr_weight.push(15.6);	//59
		arr_height.push(104.5);	arr_weight.push(15.7);	//60
		arr_height.push(107.1);	arr_weight.push(16.7);	//61
		arr_height.push(109.7);	arr_weight.push(17.7);	//62
		arr_height.push(112.3);	arr_weight.push(18.6);	//63
		arr_height.push(114.9);	arr_weight.push(19.6);	//64
		arr_height.push(117.5);	arr_weight.push(20.6);	//65
		arr_height.push(120.3);	arr_weight.push(21.8);	//66
		arr_height.push(123);	arr_weight.push(23);	//67
		arr_height.push(125.5);	arr_weight.push(24);	//68
		arr_height.push(128);	arr_weight.push(25);	//69
		arr_height.push(131);	arr_weight.push(26.6);	//70
		arr_height.push(134);	arr_weight.push(28.1);	//71
		arr_height.push(137.5);	arr_weight.push(30.3);	//72
		arr_height.push(141);	arr_weight.push(32.5);	//73
		arr_height.push(143.8);	arr_weight.push(34.8);	//74
		arr_height.push(146.5);	arr_weight.push(37);	//75
		arr_height.push(148.5);	arr_weight.push(38.7);	//76
		arr_height.push(150.5);	arr_weight.push(40.4);	//77
		arr_height.push(151.3);	arr_weight.push(41.7);	//78
		arr_height.push(152);	arr_weight.push(43);	//79
		arr_height.push(152.5);	arr_weight.push(43.8);	//80
		arr_height.push(153);	arr_weight.push(44.6);	//81
		arr_height.push(153.5);	arr_weight.push(44.8);	//82
		arr_height.push(154);	arr_weight.push(45);	//83
		arr_height.push(154);	arr_weight.push(45.2);	//84
		arr_height.push(154);	arr_weight.push(45.4);	//85
		arr_height.push(154);	arr_weight.push(45.7);	//86
		arr_height.push(154);	arr_weight.push(45.9);	//87		
		arr.push(arr_height);
		arr.push(arr_weight);
		return arr
}
/*取得生長曲線的參數
 */
function cur_get_percentile_arr_f50(){
	var arr_age = new Array();		arr_age = cur_get_percentile_arr_age();
	var arr_height = new Array();	var arr_weight = new Array();	var arr = new Array;	
		arr_height.push(49.1);	arr_weight.push(3.2);	//0
		arr_height.push(54);	arr_weight.push(4.1);	//1
		arr_height.push(57);	arr_weight.push(5);		//2
		arr_height.push(60);	arr_weight.push(5.9);	//3
		arr_height.push(62);	arr_weight.push(6.4);	//4
		arr_height.push(64);	arr_weight.push(6.9);	//5
		arr_height.push(65.7);	arr_weight.push(7.3);	//6
		arr_height.push(67);	arr_weight.push(7.6);	//7
		arr_height.push(69);	arr_weight.push(8);		//8
		arr_height.push(70);	arr_weight.push(8.2);	//9
		arr_height.push(71.5);	arr_weight.push(8.5);	//10
		arr_height.push(73);	arr_weight.push(8.7);	//11
		arr_height.push(74);	arr_weight.push(8.9);	//12
		arr_height.push(75);	arr_weight.push(9.1);	//13
		arr_height.push(76.5);	arr_weight.push(9.4);	//14
		arr_height.push(77.5);	arr_weight.push(9.6);	//15
		arr_height.push(78.5);	arr_weight.push(9.8);	//16
		arr_height.push(80);	arr_weight.push(10);	//17
		arr_height.push(80.7);	arr_weight.push(10.2);	//18
		arr_height.push(81.9);	arr_weight.push(10.4);	//19
		arr_height.push(83);	arr_weight.push(10.6);	//20
		arr_height.push(83.7);	arr_weight.push(10.8);	//21
		arr_height.push(84.6);	arr_weight.push(11);	//22
		arr_height.push(85.5);	arr_weight.push(11.2);	//23
		arr_height.push(86.4);	arr_weight.push(11.5);	//24
		arr_height.push(86.6);	arr_weight.push(11.7);	//25
		arr_height.push(87.5);	arr_weight.push(11.9);	//26
		arr_height.push(88);	arr_weight.push(12.1);	//27
		arr_height.push(89);	arr_weight.push(12.3);	//28
		arr_height.push(90);	arr_weight.push(12.5);	//29
		arr_height.push(90.7);	arr_weight.push(12.7);	//30
		arr_height.push(91.5);	arr_weight.push(12.9);	//31
		arr_height.push(92);	arr_weight.push(13.1);	//32
		arr_height.push(93);	arr_weight.push(13.3);	//33
		arr_height.push(93.6);	arr_weight.push(13.5);	//34
		arr_height.push(94.3);	arr_weight.push(13.6);	//35
		arr_height.push(95.1);	arr_weight.push(13.9);	//36
		arr_height.push(95.7);	arr_weight.push(14);	//37
		arr_height.push(96.5);	arr_weight.push(14.2);	//38
		arr_height.push(97);	arr_weight.push(14.4);	//39
		arr_height.push(98);	arr_weight.push(14.6);	//40
		arr_height.push(98.5);	arr_weight.push(14.8);	//41
		arr_height.push(99);	arr_weight.push(15);	//42
		arr_height.push(99.9);	arr_weight.push(15.2);	//43
		arr_height.push(100.4);	arr_weight.push(15.3);	//44
		arr_height.push(101);	arr_weight.push(15.5);	//45
		arr_height.push(101.6);	arr_weight.push(15.7);	//46
		arr_height.push(102);	arr_weight.push(15.9);	//47
		arr_height.push(102.7);	arr_weight.push(16.1);	//48
		arr_height.push(103.2);	arr_weight.push(16.2);	//49
		arr_height.push(104);	arr_weight.push(16.4);	//50
		arr_height.push(104.5);	arr_weight.push(16.6);	//51
		arr_height.push(105);	arr_weight.push(16.8);	//52
		arr_height.push(105.5);	arr_weight.push(17);	//53
		arr_height.push(106.2);	arr_weight.push(17.2);	//54
		arr_height.push(106.9);	arr_weight.push(17.4);	//55
		arr_height.push(107.1);	arr_weight.push(17.5);	//56
		arr_height.push(108);	arr_weight.push(17.7);	//57
		arr_height.push(108.2);	arr_weight.push(17.9);	//58
		arr_height.push(109);	arr_weight.push(18);	//59
		arr_height.push(109.4);	arr_weight.push(18.2);	//60
		arr_height.push(112.1);	arr_weight.push(19.4);	//61
		arr_height.push(114.8);	arr_weight.push(20.5);	//62
		arr_height.push(117.6);	arr_weight.push(21.7);	//63
		arr_height.push(120.3);	arr_weight.push(22.8);	//64
		arr_height.push(123);	arr_weight.push(24);	//65
		arr_height.push(125.8);	arr_weight.push(25.4);	//66
		arr_height.push(128.5);	arr_weight.push(26.8);	//67
		arr_height.push(131.3);	arr_weight.push(28.2);	//68
		arr_height.push(134);	arr_weight.push(29.6);	//69
		arr_height.push(137.5);	arr_weight.push(31.8);	//70
		arr_height.push(141);	arr_weight.push(34);	//71
		arr_height.push(144.5);	arr_weight.push(36.9);	//72
		arr_height.push(148);	arr_weight.push(39.7);	//73
		arr_height.push(150.5);	arr_weight.push(41.7);	//74
		arr_height.push(153);	arr_weight.push(43.7);	//75
		arr_height.push(154.5);	arr_weight.push(45.4);	//76
		arr_height.push(156);	arr_weight.push(47);	//77
		arr_height.push(156.8);	arr_weight.push(48.1);	//78
		arr_height.push(157.5);	arr_weight.push(49.1);	//79
		arr_height.push(157.9);	arr_weight.push(49.6);	//80
		arr_height.push(158.3);	arr_weight.push(50);	//81
		arr_height.push(158.7);	arr_weight.push(50.5);	//82
		arr_height.push(159);	arr_weight.push(51);	//83
		arr_height.push(159.3);	arr_weight.push(51);	//84
		arr_height.push(159.5);	arr_weight.push(51);	//85
		arr_height.push(159.5);	arr_weight.push(51);	//86
		arr_height.push(159.5);	arr_weight.push(51);	//87		
		arr.push(arr_height);
		arr.push(arr_weight);
		return arr
}
/*取得生長曲線的參數
 */
function cur_get_percentile_arr_f85(){
	var arr_age = new Array();		arr_age = cur_get_percentile_arr_age();
	var arr_height = new Array();	var arr_weight = new Array();	var arr = new Array;	
		arr_height.push(51.1);	arr_weight.push(3.7);	//0
		arr_height.push(56);	arr_weight.push(4.9);	//1
		arr_height.push(59);	arr_weight.push(5.9);	//2
		arr_height.push(62);	arr_weight.push(6.7);	//3
		arr_height.push(64.5);	arr_weight.push(7.2);	//4
		arr_height.push(66.5);	arr_weight.push(7.8);	//5
		arr_height.push(68.1);	arr_weight.push(8.3);	//6
		arr_height.push(69.5);	arr_weight.push(8.7);	//7
		arr_height.push(71);	arr_weight.push(9);		//8
		arr_height.push(72.5);	arr_weight.push(9.3);	//9
		arr_height.push(74);	arr_weight.push(9.6);	//10
		arr_height.push(75.5);	arr_weight.push(9.9);	//11
		arr_height.push(76.7);	arr_weight.push(10.2);	//12
		arr_height.push(78);	arr_weight.push(10.4);	//13
		arr_height.push(79);	arr_weight.push(10.7);	//14
		arr_height.push(80.5);	arr_weight.push(11);	//15
		arr_height.push(81.5);	arr_weight.push(11.1);	//16
		arr_height.push(82.5);	arr_weight.push(11.3);	//17
		arr_height.push(83.7);	arr_weight.push(11.6);	//18
		arr_height.push(85);	arr_weight.push(11.9);	//19
		arr_height.push(86);	arr_weight.push(12.1);	//20
		arr_height.push(87);	arr_weight.push(12.3);	//21
		arr_height.push(88);	arr_weight.push(12.6);	//22
		arr_height.push(89);	arr_weight.push(12.8);	//23
		arr_height.push(89.8);	arr_weight.push(13.1);	//24
		arr_height.push(90);	arr_weight.push(13.4);	//25
		arr_height.push(91);	arr_weight.push(13.5);	//26
		arr_height.push(92);	arr_weight.push(13.8);	//27
		arr_height.push(93);	arr_weight.push(14);	//28
		arr_height.push(93.5);	arr_weight.push(14.2);	//29
		arr_height.push(94.3);	arr_weight.push(14.5);	//30
		arr_height.push(95);	arr_weight.push(14.7);	//31
		arr_height.push(96);	arr_weight.push(15);	//32
		arr_height.push(96.7);	arr_weight.push(15.2);	//33
		arr_height.push(97.5);	arr_weight.push(15.4);	//34
		arr_height.push(98.1);	arr_weight.push(15.6);	//35
		arr_height.push(99);	arr_weight.push(15.9);	//36
		arr_height.push(100);	arr_weight.push(16.1);	//37
		arr_height.push(100.5);	arr_weight.push(16.3);	//38
		arr_height.push(101);	arr_weight.push(16.6);	//39
		arr_height.push(102);	arr_weight.push(16.8);	//40
		arr_height.push(102.6);	arr_weight.push(17);	//41
		arr_height.push(103.3);	arr_weight.push(17.3);	//42
		arr_height.push(104);	arr_weight.push(17.5);	//43
		arr_height.push(104.8);	arr_weight.push(17.7);	//44
		arr_height.push(105);	arr_weight.push(17.9);	//45
		arr_height.push(106);	arr_weight.push(18.2);	//46
		arr_height.push(106.6);	arr_weight.push(18.4);	//47
		arr_height.push(107.2);	arr_weight.push(18.6);	//48
		arr_height.push(108);	arr_weight.push(18.9);	//49
		arr_height.push(108.5);	arr_weight.push(19);	//50
		arr_height.push(109);	arr_weight.push(19.3);	//51
		arr_height.push(109.8);	arr_weight.push(19.5);	//52
		arr_height.push(110.1);	arr_weight.push(19.7);	//53
		arr_height.push(110.9);	arr_weight.push(20);	//54
		arr_height.push(111.5);	arr_weight.push(20.2);	//55
		arr_height.push(112);	arr_weight.push(20.4);	//56
		arr_height.push(112.7);	arr_weight.push(20.7);	//57
		arr_height.push(113.1);	arr_weight.push(20.9);	//58
		arr_height.push(114);	arr_weight.push(21.1);	//59
		arr_height.push(114.4);	arr_weight.push(21.3);	//60
		arr_height.push(117.1);	arr_weight.push(22.7);	//61
		arr_height.push(119.9);	arr_weight.push(24.2);	//62
		arr_height.push(122.6);	arr_weight.push(25.6);	//63
		arr_height.push(125.4);	arr_weight.push(27.1);	//64
		arr_height.push(128.1);	arr_weight.push(28.5);	//65
		arr_height.push(131.3);	arr_weight.push(30.8);	//66
		arr_height.push(134.5);	arr_weight.push(33);	//67
		arr_height.push(137.8);	arr_weight.push(35);	//68
		arr_height.push(141);	arr_weight.push(36.9);	//69
		arr_height.push(144.8);	arr_weight.push(39.8);	//70
		arr_height.push(148.5);	arr_weight.push(42.7);	//71
		arr_height.push(151.8);	arr_weight.push(45.5);	//72
		arr_height.push(155);	arr_weight.push(48.2);	//73
		arr_height.push(157);	arr_weight.push(50.1);	//74
		arr_height.push(159);	arr_weight.push(52);	//75
		arr_height.push(160.3);	arr_weight.push(53.5);	//76
		arr_height.push(161.5);	arr_weight.push(55);	//77
		arr_height.push(162.3);	arr_weight.push(56);	//78
		arr_height.push(163);	arr_weight.push(57);	//79
		arr_height.push(163.5);	arr_weight.push(57.5);	//80
		arr_height.push(164);	arr_weight.push(58);	//81
		arr_height.push(164.2);	arr_weight.push(58);	//82
		arr_height.push(164.4);	arr_weight.push(58);	//83
		arr_height.push(164.7);	arr_weight.push(58);	//84
		arr_height.push(165);	arr_weight.push(58);	//85
		arr_height.push(165);	arr_weight.push(58);	//86
		arr_height.push(165);	arr_weight.push(58);	//87		
		arr.push(arr_height);
		arr.push(arr_weight);
		return arr
}
/*取得生長曲線的參數
 */
function cur_get_percentile_arr_f97(){
	var arr_age = new Array();		arr_age = cur_get_percentile_arr_age();
	var arr_height = new Array();	var arr_weight = new Array();	var arr = new Array;	
		arr_height.push(52.7);	arr_weight.push(4.2);	//0
		arr_height.push(57);	arr_weight.push(5.4);	//1
		arr_height.push(61);	arr_weight.push(6.5);	//2
		arr_height.push(63.5);	arr_weight.push(7.4);	//3
		arr_height.push(66);	arr_weight.push(8);		//4
		arr_height.push(68);	arr_weight.push(8.6);	//5
		arr_height.push(70);	arr_weight.push(9.2);	//6
		arr_height.push(71.5);	arr_weight.push(9.6);	//7
		arr_height.push(73);	arr_weight.push(10);	//8
		arr_height.push(75);	arr_weight.push(10.3);	//9
		arr_height.push(76);	arr_weight.push(10.8);	//10
		arr_height.push(77.5);	arr_weight.push(11);	//11
		arr_height.push(78.9);	arr_weight.push(11.3);	//12
		arr_height.push(80);	arr_weight.push(11.7);	//13
		arr_height.push(81.5);	arr_weight.push(11.9);	//14
		arr_height.push(83);	arr_weight.push(12.2);	//15
		arr_height.push(84);	arr_weight.push(12.4);	//16
		arr_height.push(85);	arr_weight.push(12.7);	//17
		arr_height.push(86.2);	arr_weight.push(13);	//18
		arr_height.push(87.1);	arr_weight.push(13.2);	//19
		arr_height.push(88.1);	arr_weight.push(13.6);	//20
		arr_height.push(89.5);	arr_weight.push(13.8);	//21
		arr_height.push(90.5);	arr_weight.push(14);	//22
		arr_height.push(91.5);	arr_weight.push(14.3);	//23
		arr_height.push(92.5);	arr_weight.push(14.6);	//24
		arr_height.push(93);	arr_weight.push(14.8);	//25
		arr_height.push(94);	arr_weight.push(15.1);	//26
		arr_height.push(94.9);	arr_weight.push(15.4);	//27
		arr_height.push(95.4);	arr_weight.push(15.6);	//28
		arr_height.push(96.5);	arr_weight.push(16);	//29
		arr_height.push(97.3);	arr_weight.push(16.2);	//30
		arr_height.push(98);	arr_weight.push(16.4);	//31
		arr_height.push(99);	arr_weight.push(16.7);	//32
		arr_height.push(100);	arr_weight.push(17);	//33
		arr_height.push(100.7);	arr_weight.push(17.2);	//34
		arr_height.push(101.5);	arr_weight.push(17.6);	//35
		arr_height.push(102.2);	arr_weight.push(17.8);	//36
		arr_height.push(103);	arr_weight.push(18.1);	//37
		arr_height.push(104);	arr_weight.push(18.3);	//38
		arr_height.push(104.5);	arr_weight.push(18.6);	//39
		arr_height.push(105);	arr_weight.push(18.9);	//40
		arr_height.push(106);	arr_weight.push(19.2);	//41
		arr_height.push(106.7);	arr_weight.push(19.5);	//42
		arr_height.push(107.4);	arr_weight.push(19.7);	//43
		arr_height.push(108);	arr_weight.push(20);	//44
		arr_height.push(109);	arr_weight.push(20.3);	//45
		arr_height.push(109.5);	arr_weight.push(20.5);	//46
		arr_height.push(110);	arr_weight.push(20.8);	//47
		arr_height.push(110.8);	arr_weight.push(21.1);	//48
		arr_height.push(111.4);	arr_weight.push(21.4);	//49
		arr_height.push(112);	arr_weight.push(21.6);	//50
		arr_height.push(113);	arr_weight.push(22);	//51
		arr_height.push(113.5);	arr_weight.push(22.2);	//52
		arr_height.push(114);	arr_weight.push(22.4);	//53
		arr_height.push(114.7);	arr_weight.push(22.8);	//54
		arr_height.push(115.2);	arr_weight.push(23);	//55
		arr_height.push(116);	arr_weight.push(23.5);	//56
		arr_height.push(116.5);	arr_weight.push(23.6);	//57
		arr_height.push(117);	arr_weight.push(23.9);	//58
		arr_height.push(118);	arr_weight.push(24.2);	//59
		arr_height.push(118.4);	arr_weight.push(24.4);	//60
		arr_height.push(121.3);	arr_weight.push(26.5);	//61
		arr_height.push(124.2);	arr_weight.push(28.6);	//62
		arr_height.push(127.2);	arr_weight.push(30.8);	//63
		arr_height.push(130.1);	arr_weight.push(32.9);	//64
		arr_height.push(133);	arr_weight.push(35);	//65
		arr_height.push(136.5);	arr_weight.push(37.8);	//66
		arr_height.push(140);	arr_weight.push(40.5);	//67
		arr_height.push(143.5);	arr_weight.push(42.8);	//68
		arr_height.push(147);	arr_weight.push(45);	//69
		arr_height.push(150.8);	arr_weight.push(47.3);	//70
		arr_height.push(154.5);	arr_weight.push(49.6);	//71
		arr_height.push(157.3);	arr_weight.push(52.7);	//72
		arr_height.push(160);	arr_weight.push(55.8);	//73
		arr_height.push(161.8);	arr_weight.push(57.8);	//74
		arr_height.push(163.5);	arr_weight.push(59.7);	//75
		arr_height.push(164.8);	arr_weight.push(61.2);	//76
		arr_height.push(166);	arr_weight.push(62.7);	//77
		arr_height.push(167);	arr_weight.push(63.9);	//78
		arr_height.push(167.9);	arr_weight.push(65);	//79
		arr_height.push(168.2);	arr_weight.push(65.5);	//80
		arr_height.push(168.5);	arr_weight.push(66);	//81
		arr_height.push(168.8);	arr_weight.push(66.2);	//82
		arr_height.push(169);	arr_weight.push(66.4);	//83
		arr_height.push(169);	arr_weight.push(66.7);	//84
		arr_height.push(169);	arr_weight.push(67);	//85
		arr_height.push(169);	arr_weight.push(67);	//86
		arr_height.push(169);	arr_weight.push(67);	//87		
		arr.push(arr_height);
		arr.push(arr_weight);
		return arr
}