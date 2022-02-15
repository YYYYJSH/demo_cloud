/**
 *
 * @param requestMethod 请求方式 GET POST DELETE PUT...
 * @param url 请求地址
 * @param json 请求参数json
 * @param callback 请求回调方法
 */
$.rest=function(requestMethod,url,json,callback){
    //请求方式一律转大写  gEt   GET
    if(requestMethod!=null){
        requestMethod=requestMethod.toUpperCase();
    }
    //纯原生ajax代码
    let xmlhttp=null;
    if(window.XMLHttpRequest){
        xmlhttp=new XMLHttpRequest();
    }else{
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    //监听状态
    xmlhttp.onreadystatechange=function(){
        if(xmlhttp.readyState==4&&xmlhttp.status==200){
            callback(JSON.parse(xmlhttp.responseText));
        }
    };

    //打开网络连接
    xmlhttp.open(requestMethod,url,true);
    //把本地的存储的token拿到
    let token = localStorage.getItem("token");
    //放入请求头中
    xmlhttp.setRequestHeader("token",token);

    //请求数据
    if(requestMethod=="GET"){
        xmlhttp.send();
    }else{
        //设置请求头，后端才知道它是json
        xmlhttp.setRequestHeader("Content-type","application/json");
        //发送数据
        xmlhttp.send(JSON.stringify(json));
    }
}