/**
 * 
 */

// public 반환형 함수명 (파라미터...)

function deleteCheck(root, num){
	var url = root + "/guest/delete.do?num=" +num; // guestMVC/guest/delete.do?num=17
	alert(num);
	
	var value = window.confirm("정말로 삭제 하겠습니까?");
	
	if(value == true){
		location.href=url;
	}
	
}

function updateCheck(root,num){
	var url = root + "/guest/update.do?num="+num;
	alert(url);
	
	location.href=url;
}