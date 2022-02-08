import { memo } from 'react';

const SearchBar = ({ name, searchText, onChange, onKeyDown }) => {

  return (
    <div className={"search_input"}>
      <input
        name={name}
        value={searchText}
        onChange={onChange}
        onKeyDown={onKeyDown}
        placeholder={'검색어를 입력 후 엔터를 눌러주세요.'}
      />
    </div>
  );
};

export default memo(SearchBar);
