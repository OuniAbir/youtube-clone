import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {MatChipInputEvent} from "@angular/material/chips";
import {COMMA, ENTER} from "@angular/cdk/keycodes";

@Component({
  selector: 'app-save-video-details',
  templateUrl: './save-video-details.component.html',
  styleUrls: ['./save-video-details.component.css']
})
export class SaveVideoDetailsComponent implements OnInit {

  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  tags: String[] = [];

  saveVideoDetailsForm : FormGroup ;
  title : FormControl = new FormControl('');
  description : FormControl = new FormControl('');
  videoStatus : FormControl = new FormControl('');
  constructor() {
    this.saveVideoDetailsForm = new FormGroup({
      title: this.title,
      description : this.description,
      videoStatus : this.videoStatus
    });
  }

  ngOnInit(): void {
  }

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.tags.push(value);
    }

    // Clear the input value
    if (event.input) {
      event.input.value = '';
    }
  }

  remove(tag: String): void {
    const index = this.tags.indexOf(tag);

    if (index >= 0) {
      this.tags.splice(index, 1);
    }
  }

}
