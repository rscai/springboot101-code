var loadPosts = function(){
    $.ajax({
        type: "GET",
        url: "/api/post",
        dataType: "json"
    }).then(function(data) {
       data.forEach(function(post){
         var html = '<div class="row"><div class="card col-12"><div class="card-body"><h5 class="card-title">Created At: '+new Date(post.createdAt)+'</h5><p>'+post.content+'</p><a href="/html/post.html?postId='+post.id+'" class="btn btn-primary">Details</a></div></div></div>';
         $(html).appendTo("#postList");
       });
    });
}

$(document).ready(function() {
  $("#createPostForm").submit(function(event){
    console.log("submit create post");
    console.log(this.content.value);
    
    $.ajax({
        type:"POST",
        url:"/api/post",
        dataType:"json",
        data: JSON.stringify({ 
            content: this.content.value
        }),
        contentType: "application/json; charset=utf-8",
    }).then(function(data){
        var html = '<div class="row"><div class="card col-12"><div class="card-body alert-success"><h5 class="card-title">Created At: '+new Date(data.createdAt)+'</h5><p>'+data.content+'</p><a href="/html/post.html?postId='+data.id+'" class="btn btn-primary">Details</a></div></div></div>';
             $(html).appendTo("#postList");
    });
    
    event.preventDefault();
  });
  
  loadPosts();

});

