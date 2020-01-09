var main = {
    init : function() {
        var _this = this;

        // btn-save라는 id를 가진 HTML 엘리멘트에 click 이벤트가 발생할 때 호출되도록 리스너 등록
        $('#btn-save').on('click', function() {
            _this.save();
        });

        // btn-update라는 id를 가진 HTML 엘리멘트에 click 이벤트가 발생할 때 호출되도록 리스너 등록
        $('#btn-update').on('click', function() {
            _this.update();
        });

        // btn-delete라는 id를 가진 HTML 엘리멘트에 click 이벤트가 발생할 때 호출되도록 리스너 등록
        $('#btn-delete').on('click', function() {
            _this.delete();
        })
    },
    save : function() {
        var data = {
            title : $('#title').val(),
            author : $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href='/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function() {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href='/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function() {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href='/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();