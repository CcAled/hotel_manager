var bookHotel = function(hotelId) {

}
var HotelList = function() {

	// 构建hotel列表对象
    var handleHotelList = function() {
    	
    	// 上下文对象路径
		var hdnContextPath = $("#hdnContextPath").val();
		var hotelServer = $("#hotelServer").val();
		debugger;
		
		var jqGrid = $("#hotelList");
        jqGrid.jqGrid({  
            caption: "所有酒店列表",
            url:"/queryHotelList",
            mtype: "post",  
            styleUI: 'Bootstrap',//设置jqgrid的全局样式为bootstrap样式  
            datatype: "json",  
            colNames: ['ID', '酒店名称','酒店星级', '酒店地址', '附近的地标风景','早餐','设施','图片','订购'],
            colModel: [  
                { name: 'hotelId', index: 'id', width: 30 },
                { name: 'hotelName', index: 'hotelName', width: 30 },
                { name: 'starRate', index: 'starRate', width: 20 },
                { name: 'address', index: 'address', width: 20 },
                { name: 'sight', index: 'sight', width: 20 },
                { name: 'breakfast', index: 'breakfast', width: 20 },
                { name: 'facility', index: 'facility', width: 20 },
                { name: 'imgUrl', index: 'imgUrl', width: 50,
                	formatter: function(cellvalue, option, rowObject){
                		
                		var src = hotelServer + cellvalue;
                		var html = "<img href='" + src + "' ></img>"

                		
                		return html;
                	}
                },
                { name: '', index: '', width: 50, 
                	formatter: function(cellvalue, option, rowObject){
                		
                		var html = '<button class="btn btn-outline blue-chambray" id="" onclick=bookHotel("' + rowObject.id + '") style="padding: 1px 3px 1px 3px;">订购</button>';
                		
                		return html;
                	}
                }
            ],  
            viewrecords: true,  		// 定义是否要显示总记录数
            rowNum: 10,					// 在grid上显示记录条数，这个参数是要被传递到后台
            rownumbers: true,  			// 如果为ture则会在表格左边新增一列，显示行顺序号，从1开始递增。此列名为'rn'
            autowidth: true,  			// 如果为ture时，则当表格在首次被创建时会根据父元素比例重新调整表格宽度。如果父元素宽度改变，为了使表格宽度能够自动调整则需要实现函数：setGridWidth
            height: 400,				// 表格高度，可以是数字，像素值或者百分比
            rownumWidth: 20, 			// 如果rownumbers为true，则可以设置行号 的宽度
            pager: "#hotelListPager",		// 分页控件的id  
            subGrid: false				// 是否启用子表格
        }).navGrid('#hotelListPager', {
            edit: false,
            add: false,
            del: false,
            search: false
        });
        
        
        // 随着窗口的变化，设置jqgrid的宽度  
        $(window).bind('resize', function () {  
            var width = $('.hotelList_wrapper').width()*0.99;
            jqGrid.setGridWidth(width);  
        });  
        
        // 不显示水平滚动条
        jqGrid.closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
    	
    }
    
    return {
        init: function() {
            handleHotelList();
        }

    };

}();

jQuery(document).ready(function() {
	HotelList.init();
});