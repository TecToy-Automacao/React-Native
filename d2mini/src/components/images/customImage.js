import { Contente, ImgComponent } from ".";

export default function CustomImage({ source }) {
  return (
    <Contente>
      <ImgComponent source={source} />
    </Contente>
  );
}
