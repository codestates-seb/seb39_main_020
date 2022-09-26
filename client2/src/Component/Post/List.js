import React, { useEffect, useState } from 'react'
import axios from 'axios';

function List(props) {

  const [PostList, setPostList] = useState([]);

    useEffect(() => {
      
      axios.post('/list')
      .then((response) => {
        if(response.data.success) {
          setPostList([...response.data.PostList]);
        }
      })
      .catch((err) => {
        console.log(err);
      });

    }, []);
    

  return (
    <div>
    {PostList.map((post, idx) => {
      return (
        <div>
          <p>
            제목 : {post.title}
          </p>
            내용 : {post.content}
          <hr />
          </div>
      );
    })}
    </div>
  );
}

export default List;