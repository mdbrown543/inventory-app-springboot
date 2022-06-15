import React, { useState } from "react";
import { ItemsList } from "./ItemsList";
import { AddItem } from "./AddItem";
import { Video } from "./Video";

// import and prepend the api url to any fetch calls

export function App() {
  const [addClicked, setAddClicked] = useState(false);
  const [videoClicked, setVideoClicked] = useState(false);

  return (
    <main>
      <h1>Item Store</h1>
      <div>
        <button
          type="button"
          className="btn btn-primary"
          onClick={() => {
            setVideoClicked(!videoClicked);
          }}
        >
          Intro Video
        </button>
        {videoClicked && <Video />}
      </div>
      <div>
        <button
          type="button"
          className="btn btn-primary"
          onClick={() => {
            setAddClicked(!addClicked);
          }}
        >
          Add an Item
        </button>
      </div>
      {!addClicked && <ItemsList />}
      {addClicked && <AddItem setAddClicked={setAddClicked} />}
    </main>
  );
}
