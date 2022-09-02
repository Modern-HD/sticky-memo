const ch_list = [];
let info_trigger = false;

load_autocomplete()

async function update_data_to_server(mod_data) {
    try {
        const url = "/mine/modify";
        const config = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(mod_data)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function delete_data_to_server(rem_data) {
    try {
        const url = "/mine/remove";
        const config = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(rem_data)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error)
    }
}

async function logout_from_server() {
    try {
        const resp = await fetch('/user/logout');
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function mod_image_data_to_server(form_data) {
    try {
        const url = "/mine/modify_img";
        const config = {
            method: 'post',
            body: form_data
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function get_ch_list_from_server() {
    try {
        const resp = await fetch('/ch/ch_list');
        const ch_list = await resp.json();
        return await ch_list;
    } catch (error) {
        console.log(error);
    }
}

// 내비에 마우스 오버
document.getElementById('memo_nav').addEventListener('mouseover', () => {
    document.getElementById('memo_nav_section').classList.add('nav-more');
});

// 내비에 마우스 아웃
document.getElementById('memo_nav').addEventListener('mouseout', () => {
    document.getElementById('memo_nav_section').classList.remove('nav-more');
});

// 내비 create 클릭
document.querySelectorAll('.memo-nav-items').forEach( (el) => {
    if (el.dataset.type == "post") {
        el.addEventListener('click', () => {
            create_post_memo(el.dataset.color);
        })
    } else if(el.dataset.type == "image") {
        el.addEventListener('click', () => {
            open_image_memo_reg_form(0);
        });
    }
});

// 업데이트 아이콘 클릭
document.addEventListener('click', (e) => {
    if (e.target.classList.contains('update-btn')) {
        if (document.querySelector('.commit-btn') != null) {
            let updater = document.querySelector('.memo-section > .memo-content > textarea');
            updater.classList.remove('update-err');
            updater.offsetWidth;
            updater.classList.add('update-err');
            setTimeout(()=>{
            updater.classList.remove('update-err');
            }, 300);
            return;
        }
        if(e.target.closest('.memo').classList.contains('memo-img')) {
            console.log(e.target.closest('.memo').dataset.mno);
            open_image_memo_reg_form(e.target.closest('.memo').dataset.mno);
        } else {
            update_memo_form(e.target.closest('.memo').dataset.mno);
        }
    }
});

// 수정 완료 아이콘 클릭
document.addEventListener('click', (e) => {
    if (e.target.classList.contains('commit-btn')) {
        const mno_val = e.target.closest('.memo').dataset.mno;
        let content_val = document.getElementById('mod_content').value;
        content_val = content_val.replace(/</g, "&lt;");
        content_val = content_val.replace(/>/g, "&gt;");
        const mod_data = {
            mno: mno_val,
            content: content_val
        };
        update_data_to_server(mod_data).then(result => {
            if (result < 1) {
                alert("수정 중, 오류가 발생하였습니다.");
            }
            print_memo_list(pk_val);
        });
    }
});

document.addEventListener('click', (e) => {
    if (!e.target.classList.contains('bi-search') && e.target.id != "ch_search" && e.target.closest('#ch_search') == null) {
        document.getElementById('ch_search').classList.add('d-none');
    }
})

// 키보드 입력 처리
document.addEventListener('keydown', (e) => {
    if (e.key == "Escape") {
        if (!document.getElementById('file_form_modal').classList.contains('d-none')) {
            document.getElementById('file_form_modal').classList.add('d-none');
        } else if (!document.getElementById('ch_search').classList.contains('d-none')) {
            document.getElementById('ch_search').classList.add('d-none');
        } else if (document.querySelector('.commit-btn') != null) {
            print_memo_list(pk_val);
        }
    }
})

// 이미지 메모 수정 버튼 클릭
document.getElementById('image_mod_btn').addEventListener('click', () => {
    const form_data = new FormData();
    form_data.append("mno", document.getElementById('image_mno').value);
    form_data.append("file_name", encodeURIComponent(document.getElementById('image_file').value));
    form_data.append("image_file", document.getElementById('image_file').files[0]);
    mod_image_data_to_server(form_data).then(result => {
        console.log(result);
        if(result > 0) {
            print_memo_list(pk_val);
            close_all_modal();
        } else if(result == 0) {
            alert("수정에 실패하였습니다.\n 파일 이름이 같을 수 있습니다.");
            location.reload();
        } else {
            alert("수정 중에 오류가 발생하였습니다.");
            location.reload();
        }
    });
});

// 삭제 아이콘 클릭(모달 꺼내기)
document.addEventListener('click', (e) => {
    if (e.target.classList.contains('delete-btn')) {
        delete_modal_open(e.target.closest('.memo').dataset.mno);
    }
});

// 삭제 모달의 삭제 버튼 클릭
document.getElementById('del_commit_btn').addEventListener('click', () => {
    const mno_val = document.getElementById('del_mno').value;
    const rem_data = {
        mno: mno_val
    };
    delete_data_to_server(rem_data).then(result => {
        if(result > 0) {
            print_memo_list(pk_val);
        } else {
            alert("삭제 중, 오류가 발생하였습니다.");
        }
        close_all_modal();
    })
});

// 삭제 모달 아니오 버튼 클릭
document.querySelectorAll('.modal-close-btn').forEach((el) => {
    el.addEventListener('click', close_all_modal);
});

// 로그아웃 버튼 클릭
document.getElementById('logout_btn').addEventListener('click', () => {
    logout_from_server().then(() => {
        location.replace("/");
    });
});

// 메모 정보 아이콘 마우스 오버
document.addEventListener('mouseover', (e) => {
    if (e.target.classList.contains('memo-info-btn')) {
        const mno = e.target.closest('.memo').dataset.mno;
        document.querySelector(`.memo[data-mno="${mno}"] .memo-info`).classList.remove('opacity-0');
        document.querySelector(`.memo[data-mno="${mno}"] .memo-content`).classList.add('opacity-50');
    }
})

// 메모 정보 아이콘 마우스 아웃
document.addEventListener('mouseout', (e) => {
    if (e.target.classList.contains('memo-info-btn')) {
        const mno = e.target.closest('.memo').dataset.mno;
        document.querySelector(`.memo[data-mno="${mno}"] .memo-info`).classList.add('opacity-0');
        document.querySelector(`.memo[data-mno="${mno}"] .memo-content`).classList.remove('opacity-50');
    }
})

// 채널 인포
document.getElementById('title_ch_name').addEventListener('mouseover', () => {
    document.getElementById('ch_info').classList.remove('opacity-0');
    if(info_trigger) {
        get_info_from_server().then(result => {
            print_info_data(result);
        });
        info_trigger = false;
    }
})

// 채널 인포 닫기
document.getElementById('title_ch_name').addEventListener('mouseout', () => {
    document.getElementById('ch_info').classList.add('opacity-0');
})

// 채널 검색
document.querySelector('#ch_title > h1 > .bi-search').addEventListener('click', () => {
    document.getElementById('ch_search_name').value = "";
    document.getElementById('ch_search').classList.toggle('d-none');
});

// 채널 이동
document.getElementById('ch_move_btn').addEventListener('click', () => {
    const ch_name = document.getElementById('ch_search_name').value;
    location.replace("/main/" + ch_name);
})


document.querySelectorAll('#sort_menu input[type="checkbox"]').forEach(el => {
    if(el.id == 'sort_created' || el.id == 'sort_modified') {
        const another = document.getElementById(el.id == "sort_created" ? "sort_modified" : "sort_created");
        el.addEventListener('change', () => {
            if (el.checked) {
                another.checked = false;
            } else {
                another.checked = true;
            }
            print_memo_list(pk_val);
        })
    } else if(el.id != 'view_empty'){
        const another = document.getElementById(el.id == "sort_desc" ? "sort_asc" : "sort_desc");
        el.addEventListener('change', () => {
            if (el.checked) {
                another.checked = false;
            } else {
                another.checked = true;
            }
            print_memo_list(pk_val);
        })
    } else {
        el.addEventListener('change', () => {
            print_memo_list(pk_val);
        })
    }
})

document.getElementById('ch_search_name').addEventListener('focus', () => {
    if (document.getElementById('ch_search_name').value == "") {
        const print_area = document.getElementById('channels');
        print_area.innerHTML = "";
        ch_list.sort(function(a, b){return b.memo_count - a.memo_count});
        const until = ch_list.length > 10 ? 10 : ch_list.length;
        for(let i = 0; i < until; i++) {
            print_area.innerHTML += `<option value="${ch_list[i].ch_name}">`;
        }
    }
})

document.getElementById('ch_search_name').addEventListener('input', () => {
    const print_area = document.getElementById('channels');
    if (document.getElementById('ch_search_name').value != "") {
        print_area.innerHTML = "";
        ch_list.sort(function(a, b){return a.ch_name - b.ch_name});
        for(let i = 0; i < ch_list.length; i++) {
            print_area.innerHTML += `<option value="${ch_list[i].ch_name}">`;
        }
    } else {
        print_area.innerHTML = "";
        ch_list.sort(function(a, b){return b.memo_count - a.memo_count});
        const until = ch_list.length > 10 ? 10 : ch_list.length;
        for(let i = 0; i < until; i++) {
            print_area.innerHTML += `<option value="${ch_list[i].ch_name}">`;
        }
    }
})

// 이미지 메모 모달 창 열기
function open_image_memo_reg_form(mno) {
    close_all_modal();
    const modal_header = document.querySelector('#file_form_modal > .my-modal-header');
    const form_modal = document.getElementById('file_form_modal');
    const reg_btn = document.getElementById('image_reg_btn');
    const mod_btn = document.getElementById('image_mod_btn');
    const input_hidden_area = document.getElementById('file_hidden_area');
    document.getElementById('image_file').value = null;
    form_modal.classList.remove('d-none');
    if(mno == 0) {
        input_hidden_area.innerHTML = "";
        modal_header.innerText = "이미지 메모 작성";
        mod_btn.classList.add('d-none');
        reg_btn.classList.remove('d-none');
    } else if(mno > 0) {
        input_hidden_area.innerHTML = `<input type="hidden" id="image_mno" value="${mno}">`;
        modal_header.innerText = "이미지 메모 수정";
        reg_btn.classList.add('d-none');
        mod_btn.classList.remove('d-none');
    }
}

// 업데이트 폼 전환 함수
function update_memo_form(mno) {
    const memo = document.querySelector(`.memo[data-mno="${mno}"]`);
    if (memo.classList.contains('memo-img')) {

    } else {
        const header = document.querySelector(`.memo[data-mno="${mno}"] > .memo-header > div:last-child`);
        const content_area = document.querySelector(`.memo[data-mno="${mno}"] > .memo-section > .memo-content`);
        const content = document.querySelector(`.memo[data-mno="${mno}"] > .memo-section > .memo-content`).innerText;
        header.innerHTML = `<i class="bi bi-check-square commit-btn"></i>`;
        content_area.classList.remove('overflow-scroll');
        content_area.innerHTML = `<textarea cols="30" rows="10" id="mod_content">${content}</textarea>`;
    }
}

function delete_modal_open(mno) {
    const input_area = document.getElementById('delete_input');
    close_all_modal();
    input_area.innerHTML = `<input type="hidden" id="del_mno" value="${mno}">`;
    document.getElementById('delete_modal').classList.remove('d-none');
}

function close_all_modal() {
    document.querySelectorAll('.my-modal').forEach((el) => {
        el.classList.add('d-none');
        document.getElementById('ch_search').classList.add('d-none');
    })
}

function load_autocomplete() {
    get_ch_list_from_server().then(result => {
        if (typeof result == 'object') {
            result.forEach(el => {
                ch_list.push(el);
            })
        };
    });
}