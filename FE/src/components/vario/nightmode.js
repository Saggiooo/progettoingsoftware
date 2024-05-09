// Get the stored state from local storage
const storedState = localStorage.getItem('checkbox_state');

// Set the initial state from local storage or default to false
const isChecked = ref(storedState ? JSON.parse(storedState) : false);

// Update the css var based on the initial state
updateBackground(isChecked.value);

watch(isChecked, (newVal) => {
    // Store the updated state in local storage
    localStorage.setItem('checkbox_state', JSON.stringify(newVal));
    updateBackground(newVal);
});

function updateBackground(newVal: any) {
    document.documentElement.style.setProperty('--sfondo', newVal ? '#000000' : '#F2F2F2');
}