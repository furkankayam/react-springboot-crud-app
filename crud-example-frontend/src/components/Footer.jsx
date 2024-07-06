import React from "react";
import { BsEmojiSmileUpsideDown } from "react-icons/bs";

const handleSpanClick = () => {
  window.open("https://furkankayam.github.io/furkankaya.github.io/", "_blank");
};

const Footer = () => {
  return (
    <div className="w-[90%] h-[120px] my-10 rounded-xl bg-[#374151] mx-auto flex justify-center items-center selection:bg-white selection:text-[#0b1623]">
      <p className="text-white font-Consolas">
        made by{" "}
        <span
          onClick={handleSpanClick}
          className="font-bold cursor-pointer underline hover:text-[#d6ba8f] duration-300"
        >
          Mehmet Furkan Kaya
        </span>
        <BsEmojiSmileUpsideDown className="inline ml-1" />
        <br />
      </p>
    </div>
  );
};

export default Footer;
