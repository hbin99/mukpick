import styled from 'styled-components';

const Input = styled.input`
  margin-bottom: 10px;
`;


const SearchBar = ({ name, searchText, onChange, onKeyDown }) => {

  return (
    <>
      <Input
        name={name}
        value={searchText}
        onChange={onChange}
        onKeyDown={onKeyDown}
        placeholder={'검색어를 입력 후 엔터를 눌러주세요.'}
      />
    </>
  );
};

export default SearchBar;
