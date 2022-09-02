const reg = {
    nick: /^([a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{1,10}$/,
    pwd: /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/
}

const tooltip = {
    n_tip: document.getElementById('n_tooltip'),
    p_tip: document.getElementById('p_tooltip'),
}

async function nick_name_data_to_server(nick_data) {
    try {
        const url = "/user/mod_nick";
        const config = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(nick_data)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (err) {
        console.log(err);
    }
}

async function pwd_data_to_server(pwd_data) {
    try {
        const url = "/user/mod_pwd";
        const config = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(pwd_data)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(err);
    }
}

document.querySelectorAll('#mod_pwd_window input').forEach((el) => {
    switch (el.id) {
        case "p_old":
            el.addEventListener('focus', () => {
                tooltip.p_tip.innerHTML = "현재 사용 중인 비밀번호를 입력해주세요";
                tooltip.n_tip.innerHTML = "";
            });
            break;
        case "p_new":
            el.addEventListener('focus', () => {
                tooltip.p_tip.innerHTML = "영문과 숫자, 특수문자를 모두 포함한 8 ~ 16자 사용";
                tooltip.n_tip.innerHTML = "";
            });
            break;
        default:
            el.addEventListener('focus', () => {
                tooltip.p_tip.innerHTML = "";
                tooltip.n_tip.innerHTML = "";
            });
            break;
    }
});

document.getElementById('n_nick').addEventListener('focus', () => {
    tooltip.n_tip.innerHTML = "2 ~ 10자의 한글, 영어, 숫자 사용 가능";
    tooltip.p_tip.innerHTML = "";
});

document.getElementById('mod_nick_btn').addEventListener('click', () => {
    let mod_nick_btn = document.getElementById('mod_nick_btn');
    mod_nick_btn.disabled = true;
    let input_uno = document.getElementById('uno');
    let old_nick = document.getElementById('old_nick');
    let new_nick = document.getElementById('n_nick');
    if (old_nick.value == new_nick.value) {
        alert("동일한 닉네임입니다.");
        mod_nick_btn.disabled = false;
        return;
    } else if (!reg.nick.test(new_nick.value)) {
        alert("입력조건을 확인해 주세요");
        mod_nick_btn.disabled = false;
        return;
    }
    const nick_data = {
        uno: input_uno.value,
        nick_name: new_nick.value
    }
    nick_name_data_to_server(nick_data).then(result => {
        if (result > 0) {
            alert("변경 성공! 다시 로그인 해 주세요.");
            location.replace("/");
        } else {
            alert("변경 실패")
        }
        mod_nick_btn.disabled = false;
    })
});

document.getElementById('mod_pwd_btn').addEventListener('click', () => {
    let mod_pwd_btn = document.getElementById('mod_nick_btn');
    mod_pwd_btn.disabled = true;
    let input_uno =  document.getElementById('uno');
    let old_pwd = document.getElementById('p_old');
    let new_pwd = document.getElementById('p_new');
    let chk_pwd = document.getElementById('p_chk');
    if (old_pwd.value == new_pwd.value) {
        alert("현재와 동일한 비밀번호입니다.");
        new_pwd.value = "";
        old_pwd.value = "";
        chk_pwd.value = "";
        old_pwd.focus();
        mod_pwd_btn.disabled = false;
        return;
    } else if (!reg.pwd.test(new_pwd.value)) {
        alert("입력조건을 확인해 주세요");
        new_pwd.value = "";
        chk_pwd.value = "";
        new_pwd.focus();
        mod_pwd_btn.disabled = false;
        return;
    } else if (new_pwd.value != chk_pwd.value) {
        alert("비밀번호가 다릅니다.");
        chk_pwd.value = "";
        chk_pwd.focus();
        mod_pwd_btn.disabled = false;
        return;
    }
    const pwd_data = {
        "uno": input_uno.value,
        "old_pwd": old_pwd.value,
        "new_pwd": new_pwd.value
    }
    pwd_data_to_server(pwd_data).then(result => {
        if (result > 0) {
            alert("변경 성공! 다시 로그인 해 주세요.");
            location.replace("/");
        } else {
            alert("변경 실패");
            new_pwd.value = "";
            old_pwd.value = "";
            chk_pwd.value = "";
        }
        mod_pwd_btn.disabled = false;
    })
});