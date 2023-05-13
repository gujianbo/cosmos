
function setCookie(key, value, days) {
    // 设置过期时间
    let data = new Date(
        new Date().getTime() + days * 24 * 60 * 60 * 1000
    ).toUTCString();
    document.cookie = key + "=" + value + "; expires=" + data;
}
function getCookie(name) {
    let arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (arr != null) {
        return arr[2]
    } else {
        return null
    }
}
function gen_cookie_uid(){
    let rand = new Date().getTime()+Math.random()
    return "cookie_"+btoa(rand)
}
function gen_stream_id(uid){
    let rand = new Date().getTime()+Math.random()
    return "search_"+uid+"_"+btoa(rand)
}

var sub = new Vue({
    el: ".wrap",
    data: {
        keywords: "",
        anchor_keywords: "",
        stream_id: "",
        uid_code: "",
        cur_index: 1,
        next_visible: false,
        page_size: 10,
        res_list: [

        ],
        loading_visible: false,
        error_visible: false,
        error_msg: "No error!"
    },
    methods:{
        handleSubmit:function(){
            if(this.keywords.trim() == ""){
                console.log("keywords empty!")
                return
            }
            this.res_list = []
            this.cur_index = 1
            this.uid_code = getCookie("uid")
            if(this.uid_code == null){
                this.uid_code = gen_cookie_uid();
                setCookie("uid", this.uid_code, 180)
            }
            this.anchor_keywords = this.keywords.trim()
            this.stream_id = gen_stream_id(this.uid_code)
            this.loading_visible = true
            this.error_visible = false
            // this.cur_index++
            axios
                .get('/search/10002?query='+this.anchor_keywords+'&page_size=' +this.page_size+'&page_index='+this.cur_index
                    +"&stream_id="+this.stream_id+"&cookie_id="+this.uid_code)
                .then(this.allocate_results)
        },

        getNextPage:function () {
            // this.cur_index++
            axios
                .get('/search/10002?query='+this.anchor_keywords+'&page_size=' +this.page_size+'&page_index='+this.cur_index
                    +"&stream_id="+this.stream_id+"&cookie_id="+this.uid_code)
                .then(this.allocate_results)
        },

        allocate_results:function (response) {
            console.log(response)
            if(response.status == 200){
                if(response.data.rec_num >= 0){
                    let that = this
                    response.data.rec_result.forEach(function (element, index, array) {
                        let new_item = {
                            id: element.WOSId,
                            title: element.highlight_title,
                            abstract: element.abstract,
                            authors: element.authors.join(", ")
                        }
                        that.res_list.push(new_item)
                    })
                    this.cur_index += 1
                    // this.page_indexs.push(this.cur_index)
                    if(response.data.rec_num < this.page_size){
                        this.next_visible = false
                    }else{
                        this.next_visible = true
                    }
                }else{
                    this.error_visible = true
                    this.error_msg = "无结果"
                }
            }else{
                this.error_visible = true
                this.error_msg = "获取结果失败 " + response.status
            }
            this.loading_visible = false
        },

    }
})