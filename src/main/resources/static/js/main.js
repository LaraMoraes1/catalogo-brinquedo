// Modal de confirmacao de exclusao
function confirmarExclusao(id, nome) {
    const modal = document.getElementById('modal-exclusao');
    const msg = document.getElementById('modal-msg');
    const form = document.getElementById('form-exclusao');

    msg.textContent = 'Deseja excluir o produto "' + nome + '"?';
    form.action = '/admin/excluir/' + id;
    modal.style.display = 'flex';
}

function fecharModal() {
    const modal = document.getElementById('modal-exclusao');
    if (modal) modal.style.display = 'none';
}

// Fecha modal ao clicar fora
window.addEventListener('click', function(e) {
    const modal = document.getElementById('modal-exclusao');
    if (modal && e.target === modal) fecharModal();
});

// Preview de imagem antes do upload
const inputImagem = document.getElementById('arquivoImagem');
if (inputImagem) {
    inputImagem.addEventListener('change', function() {
        const file = this.files[0];
        if (!file) return;
        const reader = new FileReader();
        reader.onload = function(e) {
            let preview = document.getElementById('img-preview');
            if (!preview) {
                preview = document.createElement('img');
                preview.id = 'img-preview';
                preview.style.cssText = 'max-height:120px;border-radius:10px;margin-top:8px;border:2px solid #dfe6e9;';
                inputImagem.parentNode.appendChild(preview);
            }
            preview.src = e.target.result;
        };
        reader.readAsDataURL(file);
    });
}

// Auto-dismiss alerts
document.querySelectorAll('.alert').forEach(function(alert) {
    setTimeout(function() {
        alert.style.transition = 'opacity 0.5s';
        alert.style.opacity = '0';
        setTimeout(function() { alert.remove(); }, 500);
    }, 4000);
});
