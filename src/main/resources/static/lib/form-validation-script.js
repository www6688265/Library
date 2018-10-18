var Script = function () {
    jQuery.validator.addMethod("idcard", function(value, element) {
        var tel = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
        return this.optional(element) || (tel.test(value));
    }, "请正确填写您的身份证");

    jQuery.validator.addMethod("phone", function(value, element) {
        var tel = /^1[34578]\d{9}$/;
        return this.optional(element) || (tel.test(value));
    }, "请正确填写您的手机号码");

    jQuery.validator.addMethod("username", function(value, element) {
        var tel = /^[\u4E00-\u9FA5A-Za-z]+$/;
        return this.optional(element) || (tel.test(value));
    }, "只能输入中文或英文");

    $.validator.setDefaults({
        submitHandler: function() {
            $.ajax({url:"/user/addUser",
                type:"POST",
                dataType:"json",
                data:$("#userForm").serialize(),
                success:function (data) {
                    if(data.result=="success"){
                        alert("添加成功成功");
                        oTable.ajax.reload();
                        $("#myModal").modal("hide");
                        $("#myModalLabel").text("新增");
                        clear();
                    }
                    else{
                        alert(data.msg);
                    }
                },
                error:function () {
                    alert("网络出现问题！");
                }
            })
        }
    });

    $().ready(function() {
        // validate the comment form when it is submitted
        $("#commentForm").validate();
        $("#addBookForm").validate({
            rules:{
                bookname:{
                    required: true
                },
                type:{
                    required: true
                },
                press:{
                    required: true
                },
                total:{
                    required: true,
                    digits:true
                },
                floor:{
                    required: true,
                    digits:true
                },
                bookcase:{
                    required: true,
                    digits:true
                },
                level:{
                    required: true,
                    digits:true
                },
                isbn:{
                    required: true,
                    digits:true
                },
            },
        });

        $("#userForm").validate({
            rules:{
                username: {
                    username:true,
                    required: true,
                    minlength: 2
                },
                idcard: {
                    required: true,
                    idcard:true,
                },
                sex:{
                    required: true
                },
                usertele:{
                    required: true,
                    phone:true
                }
            },
            messages:{
                username: {
                    username:"只能输入中文或者英文",
                    required: "姓名不能为空",
                    minlength: "至少两个字"
                },
                idcard: {
                    required: "身份证号码不能为空",
                    idcard:"身份证格式不正确"
                },
                sex:{
                    required: "性别不能为空"
                },
                usertele:{
                    required:"手机号码不能为空",
                    phone:"手机号码格式不正确"
                }
            }
        });

        // validate signup form on keyup and submit
        // $("#signupForm").validate({
        //     rules: {
        //         firstname: "required",
        //         lastname: "required",
        //         username: {
        //             required: true,
        //             minlength: 2
        //         },
        //         password: {
        //             required: true,
        //             minlength: 5
        //         },
        //         confirm_password: {
        //             required: true,
        //             minlength: 5,
        //             equalTo: "#password"
        //         },
        //         email: {
        //             required: true,
        //             email: true
        //         },
        //         topic: {
        //             required: "#newsletter:checked",
        //             minlength: 2
        //         },
        //         agree: "required"
        //     },
        //     messages: {
        //         firstname: "Please enter your firstname",
        //         lastname: "Please enter your lastname",
        //         username: {
        //             required: "Please enter a username",
        //             minlength: "Your username must consist of at least 2 characters"
        //         },
        //         password: {
        //             required: "Please provide a password",
        //             minlength: "Your password must be at least 5 characters long"
        //         },
        //         confirm_password: {
        //             required: "Please provide a password",
        //             minlength: "Your password must be at least 5 characters long",
        //             equalTo: "Please enter the same password as above"
        //         },
        //         email: "Please enter a valid email address",
        //         agree: "Please accept our policy"
        //     }
        // });
        //
        // // propose username by combining first- and lastname
        // $("#username").focus(function() {
        //     var firstname = $("#firstname").val();
        //     var lastname = $("#lastname").val();
        //     if(firstname && lastname && !this.value) {
        //         this.value = firstname + "." + lastname;
        //     }
        // });
        //
        // //code to hide topic selection, disable for demo
        // var newsletter = $("#newsletter");
        // // newsletter topics are optional, hide at first
        // var inital = newsletter.is(":checked");
        // var topics = $("#newsletter_topics")[inital ? "removeClass" : "addClass"]("gray");
        // var topicInputs = topics.find("input").attr("disabled", !inital);
        // // show when newsletter is checked
        // newsletter.click(function() {
        //     topics[this.checked ? "removeClass" : "addClass"]("gray");
        //     topicInputs.attr("disabled", !this.checked);
        // });
    });


}();