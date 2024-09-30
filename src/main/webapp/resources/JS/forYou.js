function likeUnlikePost1234(postId){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            document.getElementById("likeicon1234" + postId).outerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "likepost?postid=" + postId + "&t=" + new Date().getTime(), true);
    xhttp.send();
}

function iscommentpost1234(postId) {
    var e = document.getElementById("comment1234" + postId).value;
    if(e.trim()!=''){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            document.getElementById("commenticon1234" + postId).outerHTML = this.responseText;
        }				
    };
    xhttp.open("GET", "comment1234?postid=" + postId + "&comment=" +e,  true);
    xhttp.send();
    }
    else{
		alert("Comment is null");
	}
	
}