export const getFullDateFormat = (date) => {
  const newDate = new Date(date);

  let year = newDate.getFullYear();
  let month = newDate.getMonth() + 1;
  let day = newDate.getDate();
  let hours = newDate.getHours();
  let minutes = newDate.getMinutes();
  let seconds = newDate.getSeconds();
  if (month < 10) month = '0' + month;
  if (day < 10) day = '0' + day;
  if (hours < 10) hours = '0' + hours;
  if (minutes < 10) minutes = '0' + minutes;
  if (seconds < 10) seconds = '0' + seconds;

  return ''
    .concat(year)
    .concat('-')
    .concat(month)
    .concat('-')
    .concat(day)
    .concat(' ')
    .concat(hours)
    .concat(':')
    .concat(minutes)
    .concat(':')
    .concat(seconds);
};
