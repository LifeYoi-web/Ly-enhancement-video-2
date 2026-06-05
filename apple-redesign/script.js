// ===== الوضع الليلي / النهاري =====
const root = document.documentElement;
const saved = localStorage.getItem('apple-theme');
if (saved) root.setAttribute('data-theme', saved);
document.getElementById('themeToggle').addEventListener('click', () => {
  const next = root.getAttribute('data-theme') === 'dark' ? 'light' : 'dark';
  root.setAttribute('data-theme', next);
  localStorage.setItem('apple-theme', next);
});
// أيقونة الشمس/القمر حسب الوضع
const syncIcons = () => {
  const dark = root.getAttribute('data-theme') === 'dark';
  document.querySelector('.icon-sun').style.display = dark ? 'none' : 'block';
  document.querySelector('.icon-moon').style.display = dark ? 'block' : 'none';
};
new MutationObserver(syncIcons).observe(root, { attributes: true, attributeFilter: ['data-theme'] });
syncIcons();

// ===== قائمة الجوال =====
const burger = document.getElementById('navBurger');
const menu = document.getElementById('navMenu');
burger.addEventListener('click', () => { burger.classList.toggle('open'); menu.classList.toggle('open'); });
menu.querySelectorAll('a').forEach(a => a.addEventListener('click', () => { burger.classList.remove('open'); menu.classList.remove('open'); }));

// ===== حركات ظهور خفيفة عند التمرير =====
const io = new IntersectionObserver((entries) => {
  entries.forEach(e => { if (e.isIntersecting) { e.target.style.opacity = 1; e.target.style.transform = 'none'; io.unobserve(e.target); } });
}, { threshold: 0.15 });
document.querySelectorAll('.tile-head, .feat').forEach(el => {
  el.style.opacity = 0; el.style.transform = 'translateY(28px)';
  el.style.transition = 'opacity .7s cubic-bezier(.28,.11,.32,1), transform .7s cubic-bezier(.28,.11,.32,1)';
  io.observe(el);
});
