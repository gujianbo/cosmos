
var search_res = new Vue({
    el: '.search_res',
    data: {
        msg: "hello",
        res_list:[

        ],
        loading_visible: false,
        error_visible: false,
        error_msg: "No error!"
    }
})

var foot = new Vue({
    el: '.foot',
    data: {
        page_indexs: [],
        cur_index: 0,
        next_visible: false
    }
})

var sub = new Vue({
    el: ".search_form",
    data: {
        keywords: "",
        page_index: 1,
        page_size: 10
    },
    methods:{
        handleSubmit:function(){
            if(this.keywords.trim() == ""){
                console.log("keywords empty!")
                return
            }
            search_res.$data.loading_visible = true
            search_res.$data.error_visible = false
            axios
                .get('/search/10002?query='+this.keywords+'&page_size='+this.page_size+'&page_index'+this.page_index)
                .then(this.allocate_results)
        },
        allocate_results:function (response) {
            console.log(response)
            if(response.status == 200){
                if(response.data.rec_num >= 0){
                    response.data.rec_result.forEach(function (element, index, array) {
                        let new_item = {
                            id: element.WOSId,
                            title: element.highlight_title,
                            abstract: element.abstract,
                            authors: element.authors.join(", ")
                        }
                        search_res.$data.res_list.push(new_item)
                    })
                    foot.$data.cur_index += 1
                    foot.$data.page_indexs.push(foot.$data.cur_index)
                    if(response.data.rec_num < this.page_size){
                        foot.$data.next_visible = false
                    }else{
                        foot.$data.next_visible = true
                    }
                }else{
                    search_res.$data.error_visible = true
                    search_res.$data.error_msg = "无结果"
                }
            }else{
                search_res.$data.error_visible = true
                search_res.$data.error_msg = "获取结果失败 " + response.status
            }
            search_res.$data.loading_visible = false
        }
    }
})