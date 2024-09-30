function likeUnlikePostprofile(postId){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            document.getElementById("likeiconprofile" + postId).outerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "likepostprofile?postid=" + postId + "&t=" + new Date().getTime(), true);
    xhttp.send();
}

function iscommentpost(postId) {
    var e = document.getElementById("comment" + postId).value;
    if(e.trim()!=''){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            document.getElementById("commenticon" + postId).outerHTML = this.responseText;
        }				
    };
    xhttp.open("GET", "comment?postid=" + postId + "&comment=" +e,  true);
    xhttp.send();
    }
    else{
		alert("Comment is null");
	}
}
						