$(document).ready(function() {
	$("#area_select").change(function() {
        if (!cityArea.validate()) {
            $("#result").val("請選擇完整地址!");
        } else {
            $("#result").val(cityArea.getAddress(true));
        }

    });
//三階地址JS啟動檔，#submit_btn是按鈕ID，cityArea.getAddress(是否要有郵遞區號)取得選擇的內容，可將資料設進資料庫。
  var cityArea = new CityArea('city_select', 'area_select', "xml/");
    $("#submit_btn").click(function () {    
        if( !cityArea.validate() ){
            $("#result").text("請選擇完整地址!");
        }else{
            $("#result").text(cityArea.getAddress(true));
        } 
    });
//啟動檔結束  
});
//---三階選單FUNCTION
CityArea = (function () {
    
    function CityArea(cityId, areaId, Path) {
        this.cityId = cityId;
        this.areaId = areaId;
        var jCity = $("#" + cityId);
        var jArea = $("#" + areaId);
        jCity.empty();
        jCity.append("<option value='-1'>--縣市--</option>");
        jArea.empty();
        jArea.append("<option value='0'>--鄉鎮地區--</option>");

        var innerThis = this;
        this.xml = null;
        this.$city = jCity;
        this.$area = jArea;

        function clearAreaAndStreet() {
            jArea.empty();
            jArea.append("<option value='0'>--鄉鎮地區--</option>");
        }

        jCity.change(function (e) {
            clearAreaAndStreet();
            var index = jCity.val();
            if (index == -1) {
                return;
            }
            var citys = $(xml).find("twzip").find("city")[index];
            $(citys).find("area").each(function (i) {
                var name = $(this).attr("name");
                var zip = $(this).attr("zip");
                jArea.append("<option value='" + zip + "'>" + name + "</option>");
            });
        });
      
        $.ajax({
            type: "GET",
            url: Path + "ZipCode.xml",
            dataType: 'xml',
            success: function (response) {
                if (response) {
                    xml = response;
                    $(xml).find("twzip").find("city").each(function (i) {
                        var label = $(this).attr("name");
                        jCity.append("<option value='" + i + "'>" + label + "</option>");
                    });
                }
            }
        });
    }
    CityArea.prototype.getCity = function () {
        return $("#" + this.cityId + " :selected").text();
    }
    CityArea.prototype.getArea = function () {
        return $("#" + this.areaId + " :selected").text();
    }
    CityArea.prototype.getZip = function () {
        return $("#" + this.areaId + " :selected").attr("value");
    } 
    CityArea.prototype.getAddress = function (withZip) {
        var address = this.getCity() + this.getArea();
        return withZip ? this.getZip() + address : address;
    }
    //沒有選擇完成，錯誤處理
    CityArea.prototype.validate = function(){
    var cityIndex = this.$city.get(0).selectedIndex;
    var areaIndex = this.$area.get(0).selectedIndex;
    return cityIndex != 0 && areaIndex != 0;
  }
    return CityArea;
})();

//---三階地址選單結束
