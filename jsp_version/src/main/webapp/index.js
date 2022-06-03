// general
document.getElementById('memo_nav').addEventListener('mouseover', () => {
    document.getElementById('memo_nav_section').classList.add('nav-more');
});

document.getElementById('memo_nav').addEventListener('mouseout', () => {
    document.getElementById('memo_nav_section').classList.remove('nav-more');
});

document.querySelectorAll('.memo-nav-items').forEach( (el) => {
    if (el.dataset.type = "sticky") {
        el.addEventListener('click', () => {
            create_sticky_memo(el.dataset.color);
        })
    }
});

document.addEventListener('click', (e) => {
    if (e.target.classList.contains('update-btn')) {
        if (document.querySelector('.commit-btn') != null) {
            let updater = document.querySelector('.memo-section > textarea');
            updater.classList.remove('update-err');
            updater.offsetWidth;
            updater.classList.add('update-err');
            setTimeout(()=>{
            updater.classList.remove('update-err');
            }, 300);
            return;
        }
        update_sticky_memo(e.target.dataset.id);
    }
});

document.addEventListener('click', (e) => {
    if (e.target.classList.contains('delete-btn')) {
        delete_modal_open(e.target.dataset.id);
    }
});

document.querySelectorAll('.modal-close-btn').forEach((el) => {
    el.addEventListener('click', close_all_modal);
});

function update_sticky_memo(id) {
	const memo = document.querySelector(`.memo[data-id="${id}"]`);
    memo.outerHTML = `<form action="./modify.sm" method="post">${memo.outerHTML}</form>`;
    const header = document.querySelector(`.memo[data-id="${id}"] > .memo-header`);
    const section = document.querySelector(`.memo[data-id="${id}"] > .memo-section`);
    const content = document.querySelector(`.memo[data-id="${id}"] > .memo-section`).innerText;
    header.innerHTML = `<button type="submit"><i class="bi bi-check-square commit-btn" data-id="${id}"></i></button>`;
    section.innerHTML = `<input type="hidden" name="id" value="${id}">
    <textarea cols="30" rows="10" name="content">${content}</textarea>
    `;
}

function create_sticky_memo(color) {
	let link = `./insert.sm?color=${color}`;
	location.replace(link);
}

function delete_modal_open(id) {
    const input_area = document.getElementById('delete_input');
    close_all_modal();
    input_area.innerHTML = `<input type="hidden" name="id" value="${id}">`;
    document.getElementById('delete_modal').classList.remove('d-none');
}

function close_all_modal() {
    document.querySelectorAll('.my-modal').forEach((el) => {
        el.classList.add('d-none');
    })
}