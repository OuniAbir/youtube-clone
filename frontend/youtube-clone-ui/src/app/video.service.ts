import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UploadVideoResponse} from "./upload-video/upload-video-response";


@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient: HttpClient) {
  }

  uploadVideo(fileEntry: File): Observable<UploadVideoResponse> {
    // http Post call to upload the video
    const formData = new FormData()
    formData.append('file', fileEntry, fileEntry.name)

    return this.httpClient.post<UploadVideoResponse>('http://localhost:8080/api/videos', formData);
  }
}
