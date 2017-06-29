var lv1Size = $(".stock-address-list .tab-con div:first a").size()-1;
lv1Size = 34;
var index0 = 0;
var lv2Size = 0;
var lv3Size = 0;
var index1 = 0;
var interval0 = 0;
var interval1 = 0;
var sumLv2 = 0;
var province = {};
var subCity=[];
var provinceName = "";
var st1 = new Date();
var cities = [];
function sleep(timeInMilli) {
    var now = new Date();
    var exitTime = now.getTime() + timeInMilli;
    var sleepIndex = 0;
    while (true) {
    		sleepIndex++;
        if (new Date().getTime() > exitTime)
	        	break;
    }
}
function stop() {clearInterval(interval0);clearInterval(interval1);}
function process() {
	if(index0 == lv1Size) {return ;}
	provinceName = $(".stock-address-list .tab-con div:first a")[index0].innerHTML;
	$(".stock-address-list .tab-con div:first a")[index0].click();
	console.log(provinceName + ":");
	sleep(100);
	lv2Size = $(".stock-address-list .tab-con div:eq(1) a").size();
	console.log("lv2Size="+lv2Size);
	index1 = 0;
	index0++;
//	if(index0 == lv1Size) {
//		clearInterval(interval0);
//	}
	st1 = new Date();
	interval1 = setInterval("process1()",1000);
	// sumLv2 = sumLv2 + lv2Size;
	// console.log(lv2Size);
	//if(index0 == lv1Size-1) {
	//	stop();
	//}
	// index0++;
};

function process1() {
//	for(index1=0;index1<lv2Size;index1++) {
		$(".J-address-tab .tab li:eq(1)").click();
		var cityName = $(".stock-address-list .tab-con div:eq(1) a")[index1].innerHTML;
		sleep(80);
		$(".stock-address-list .tab-con div:eq(1) a")[index1].click();
		sleep(80);
		lv3Size = $(".stock-address-list .tab-con div:eq(2) a").size();
		subCity=[];
		for(var j=0;j<lv3Size;j++) {
			subCity.push($(".stock-address-list .tab-con div:eq(2) a")[j].innerHTML);
			//subCity = subCity + $(".stock-address-list .tab-con div:eq(2) a")[j].innerHTML + ";";
		}
		var each = index1+"."+provinceName+"->" + cityName + "->" +"(" + subCity.join(',') + "," +lv3Size+")";
		cities.push(each);
		console.log(each);
//	}
		index1++;
	if(index1 == lv2Size) {
		clearInterval(interval1);
		var st2 = new Date();
		console.log(st2.getTime() - st1.getTime());
		process();
	}
}

$('.sku-name').html('<div><input value="start" type="button" onclick="start()" /><input value="stop" type="button" onclick="stop()" /></div>')
function start() {
	// interval0 = setInterval("process()",20000);
	process();
}

