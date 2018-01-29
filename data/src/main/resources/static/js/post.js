function getUrlVars()
{
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}

var postId = getUrlVars()["postId"];

var loadPost = function(postId){
    $.ajax({
        type: "GET",
        url: "/api/post/"+postId,
        dataType: "json"
    }).then(function(post) {
       var html = '<div class="card col-12"><div class="card-body alert-primary"><h5 class="card-title">Created At: '+new Date(post.createdAt)+'</h5><p>'+post.content+'</p></div></div>';
       $(html).appendTo("#postRow");
       });
};


var loadComments = function(){
    $.ajax({
        type: "GET",
        url: "/api/post/"+postId+"/comment",
        dataType: "json"
    }).then(function(data) {
       data.forEach(function(post){
         var html = '<div class="row"><div class="card col-10 comment-card"><div class="card-body"><h5 class="card-title">Created At: '+new Date(post.createdAt)+'</h5><p>'+post.content+'</p></div></div></div>';
         $(html).appendTo("#commentList");
       });
    });
}

$(document).ready(function() {
  $("#createCommentForm").submit(function(event){
    console.log("submit create comment");
    console.log(this.content.value);
    $.ajax({
        type:"POST",
        url:"/api/post/"+postId+"/comment",
        dataType:"json",
        data: JSON.stringify({ 
            content: this.content.value
        }),
        contentType: "application/json; charset=utf-8",
    }).then(function(data){
        var html = '<div class="row"><div class="card col-10"><div class="card-body alert-success"><h5 class="card-title">Created At: '+new Date(data.createdAt)+'</h5><p>'+data.content+'</p></div></div></div>';
        $(html).appendTo("#commentList");
    });
    event.preventDefault();
  });
  loadPost(postId);
  loadComments();

});